import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.util.regex.Pattern;

public class AnsvarligMineInformationerController {
    public TextField navn;
    public TextField efternavn;
    public TextField tlfNr;
    public TextField email;
    public Label personRoskildeID;
    public Button gemBnt;
    public Button redigerBtn;
    public Button passwordBtn;
    private GUI gui;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void preLoad(Person person) {
        navn.setText(person.getNavn());
        efternavn.setText(person.getEfternavn());
        tlfNr.setText(person.getTlfNr());
        email.setText(person.getEMail());
        personRoskildeID.setText(person.getRoskildeId());

        navn.setDisable(true);
        efternavn.setDisable(true);
        tlfNr.setDisable(true);
        email.setDisable(true);

        gemBnt.setVisible(false);

        Tooltip t = new Tooltip("WIP. Kommer i senere versioner");
        Tooltip.install(passwordBtn, t);//todo lav skift password

    }

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void redigerInfo(ActionEvent actionEvent) {
        gemBnt.setVisible(true);

        navn.setDisable(false);
        efternavn.setDisable(false);
        tlfNr.setDisable(false);
        email.setDisable(false);

        redigerBtn.setDisable(true);
    }

    public void gemInfo(ActionEvent actionEvent) throws IOException {
        Person person = Runner.personHashMap.get(personRoskildeID.getText().split(" ")[0]);
        boolean bNavn  = true;
        if (!Pattern.matches(RegisteringAfFriviligController.navnRegex, navn.getText())){
            bNavn = false;
        }
        boolean bEfternavn = true;
        if (!Pattern.matches(RegisteringAfFriviligController.efternavnRegex, efternavn.getText())){
            bEfternavn = false;
        }
        boolean bTlfNr = true;
        if (!Pattern.matches(RegisteringAfFriviligController.tlfNrRegex, tlfNr.getText())){
            bTlfNr = false;
        }
        boolean bEMail = true;
        if (!Pattern.matches(RegisteringAfFriviligController.eMailRegex, email.getText())){
            bEMail = false;
        }

        if (bNavn && bEfternavn && bTlfNr && bEMail){
            person.setNavn(navn.getText());
            person.setEfternavn(efternavn.getText());
            person.setTlfNr(tlfNr.getText());
            person.setEMail(email.getText());

            gui.setAnsvarligMineInformationer(person);
        } else {
            String error = "";
            if (!bNavn) {
                error += "Fejl i navn:\nNavn skal starte med store bogstaver, og må kun indhold alfabetiske karakter og \"-\"\n\r";
            }
            if (!bEfternavn){
                error += "Fejl i efternavn:\nEfternavn må kun indhold alfabetiske karakter og \" \", \"-\"\n\r";
            }
            if (!bTlfNr){
                error += "Fejl i telefonnummer:\nTelefonnummer må kun bestå af tal og kan indhold \"+\" efterfulgt af landekode\n\r";
            }
            if (!bEMail){
                error += "Fejl i email:\nEmail skal være gyldig email format. fx \"xxxx@xxxx.xxx\"\n\r";
            }
            GUI.infoBox(error,"Fejl i redigering af person informationer");
        }
    }
}
