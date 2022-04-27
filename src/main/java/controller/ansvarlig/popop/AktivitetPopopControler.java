package controller.ansvarlig.popop;

import Main.*;
import controller.ansvarlig.AnsvarligVagterController;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;

public class AktivitetPopopControler {
    public Aktivitet newAktivitet;
    public Stage stage;
    public AnsvarligVagterController ansvarligVagterController;
    public TextField aktivitetsTitel;
    public TextArea beskrivelse;
    public TextField lokation;
    public ChoiceBox ansvarligeListe;

    public void tilbage(ActionEvent actionEvent) {
        stage.close();
    }


    public void opretAktivtet(ActionEvent actionEvent) {
        //hent info
        boolean titelB = false;
        String titel = "";
        if(!aktivitetsTitel.getText().equals("")){
            titel = aktivitetsTitel.getText();
            titelB = true;
        }

        boolean beskrivelseB = false;
        String beskrivelseS = "";
        if (!beskrivelse.getText().equals("")){
            beskrivelseS = beskrivelse.getText();
            beskrivelseB = true;
        }

        boolean lokationB = false;
        String lokationS = "";
        if (!lokation.getText().equals("")){
            lokationS = lokation.getText();
            lokationB = true;
        }

        String ansvarligRosId = "";
        boolean ansvarligB = false;
        String ansvarligS = (String) ansvarligeListe.getValue();
        if (!(ansvarligS.equals("") || ansvarligS == null)){
            ansvarligRosId = ansvarligS.split("-")[1].substring(1);
            ansvarligB = true;
        }


        if (titelB && beskrivelseB && lokationB && ansvarligB) {
            newAktivitet = new Aktivitet(titel, beskrivelseS, lokationS, ansvarligRosId);
            DatabaseLink.aktivteter.add(newAktivitet);
            ansvarligVagterController.done();
            stage.close();
        }
        //todo skriv fejl

    }

    public void preeload() {
        for(Map.Entry<String, Person> entry : DatabaseLink.personHashMap.entrySet()) {
            String key = entry.getKey();
            if (key.charAt(0) == 'A') {
                Person person = entry.getValue();
                ansvarligeListe.getItems().add("" + person.getNavn() + " " + person.getEfternavn() + " - " + person.getRoskildeId());
            }
        }
    }
}
