import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Pattern;

public class FriviligMineInformationerController {
    public TextField navn;
    public TextField efternavn;
    public TextField tlfNr;
    public TextField email;
    public Label personRoskildeID;
    public Button gemBnt;
    public Button redigerBtn;
    private GUI gui;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void preLoad(Person person){
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

    public void gemInfo(ActionEvent actionEvent) {
        Person person = Runner.personHashMap.get(personRoskildeID.getText().split(" ")[0]);
        if (Pattern.matches("^[A-ZÆØÅ]{1}[a-zæøåü\\-]*", navn.getText())){

        }
    }
}
