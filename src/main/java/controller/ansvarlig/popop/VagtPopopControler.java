package controller.ansvarlig.popop;

import Main.Aktivitet;
import Main.DatabaseLink;
import Main.Person;
import Main.Vagt;
import controller.ansvarlig.AnsvarligVagterController;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Map;

public class VagtPopopControler {
    public Stage stage;
    public AnsvarligVagterController ansvarligVagterController;
    public ChoiceBox friviligChoiceBox;
    public ChoiceBox startTidspunktChoiceBox;
    public DatePicker startDatoDatePicker;
    public ChoiceBox slutTidspunktChoiceBox;
    public DatePicker slutDatoDatePicker;
    public Aktivitet aktivitet;

    public void preeload() {
        for(Map.Entry<String, Person> entry : DatabaseLink.personHashMap.entrySet()) {
            String key = entry.getKey();
            if (key.charAt(0) == 'F') {
                Person person = entry.getValue();
                friviligChoiceBox.getItems().add("" + person.getNavn() + " " + person.getEfternavn() + " - " + person.getRoskildeId());
            }
        }

        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 4; j++) {
                String temp = String.format("%02d:%02d" , i , (j*15));
                startTidspunktChoiceBox.getItems().add(temp);
                slutTidspunktChoiceBox.getItems().add(temp);
            }
        }

    }

    public void tilbage(ActionEvent actionEvent) {
        stage.close();
    }


    public void opretVagt(ActionEvent actionEvent) {
        boolean ready = true;
        if (startTidspunktChoiceBox.getValue()==null)
            ready = false;
        if (startDatoDatePicker.getValue()== null)
            ready = false;
        if (slutTidspunktChoiceBox.getValue() == null)
            ready = false;
        if (slutDatoDatePicker.getValue() == null)
            ready = false;

        if (ready) {
            System.out.println(DatabaseLink.aktivteter.get(aktivitet.getId() - 1).getVagter().size());
            Calendar startTidspunkt;
            Calendar slutTidspunkt;
            int hh;
            int mm;
            hh = Integer.parseInt(((String) startTidspunktChoiceBox.getValue()).split(":")[0]);
            mm = Integer.parseInt(((String) startTidspunktChoiceBox.getValue()).split(":")[1]);
            startTidspunkt = Calendar.getInstance();
            startTidspunkt.set(startDatoDatePicker.getValue().getYear(), startDatoDatePicker.getValue().getMonth().getValue(), startDatoDatePicker.getValue().getDayOfMonth(), hh, mm);

            hh = Integer.parseInt(((String) slutTidspunktChoiceBox.getValue()).split(":")[0]);
            mm = Integer.parseInt(((String) slutTidspunktChoiceBox.getValue()).split(":")[1]);
            slutTidspunkt = Calendar.getInstance();
            slutTidspunkt.set(slutDatoDatePicker.getValue().getYear(), slutDatoDatePicker.getValue().getMonth().getValue(), slutDatoDatePicker.getValue().getDayOfMonth(), hh, mm);
            String firvilig;
            if (friviligChoiceBox.getValue()!= null) {
                firvilig = friviligChoiceBox.getValue().toString().split("-")[1].substring(1);
            } else {
                firvilig = "x";
            }
            DatabaseLink.aktivteter.get(aktivitet.getId() - 1).addVagt(new Vagt(firvilig, startTidspunkt, slutTidspunkt,aktivitet));
            ansvarligVagterController.loadVagter();
            System.out.println("ja");
            System.out.println(DatabaseLink.aktivteter.get(aktivitet.getId() - 1).getVagter().size());
            stage.close();
        } else {
            //todo informer om fejl
        }
    }
}
