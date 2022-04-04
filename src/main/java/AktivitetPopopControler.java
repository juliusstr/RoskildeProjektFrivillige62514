import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;
import java.util.regex.Pattern;

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
        if(!Pattern.matches(RegisteringAfFriviligController.efternavnRegex, aktivitetsTitel.getText())){
            titel = aktivitetsTitel.getText();
            titelB = true;
        }
        boolean beskrivelseB = false;
        String beskrivelseS = "";
        if (beskrivelse.getText().equals("")){
            beskrivelseS = beskrivelse.getText();
            beskrivelseB = true;
        }

        boolean lokationB = false;
        String lokationS = "";
        if (beskrivelse.getText().equals("")){
            lokationS = lokation.getText();
            lokationB = true;
        }



        if (titelB && beskrivelseB && lokationB) {
            //newAktivitet = new Aktivitet(titel, beskrivelseS, lokationS)
            //ansvarligVagterController.done();
            //stage.close();
        }

    }

    public void preeload() {
        for(Map.Entry<String, Person> entry : DatabaseLink.personHashMap.entrySet()) {
            String key = entry.getKey();
            if (key.charAt(0) == 'A') {
                Person person = entry.getValue();
                System.out.println(key);
                ansvarligeListe.getItems().add("" + person.getNavn() + " " + person.getEfternavn() + " - " + person.getRoskildeId());
            }
        }
    }
}
