import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

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
                System.out.println(key);
                friviligChoiceBox.getItems().add("" + person.getNavn() + " " + person.getEfternavn() + " - " + person.getRoskildeId());
            }
        }

        for (int i = 0; i < 24; i++) {// todo skriv kl på en bedere måde
            for (int j = 0; j < 4; j++) {
                startTidspunktChoiceBox.getItems().add(""+i+":"+j*15);
                slutTidspunktChoiceBox.getItems().add(""+i+":"+j*15);
            }
        }

    }

    public void tilbage(ActionEvent actionEvent) {
        stage.close();
    }
}
