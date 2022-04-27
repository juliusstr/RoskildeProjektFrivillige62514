package controller.ansvarlig.popop;

import Main.*;
import controller.ansvarlig.SeFriviligAnsvarligController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnsvarligRedigerFriviligInformationerPopopController {

    public Stage stage;
    public SeFriviligAnsvarligController seFriviligAnsvarligController;
    public TextField tlfNr;
    public TextField email;
    public TextField navn;
    public TextField efternavn;
    public Label personRoskildeID;
    public TextField password;
    public TextField bdag;
    Person person;

    public void preeload(Person person) {
        navn.setText(person.getNavn());
        efternavn.setText(person.getEfternavn());
        tlfNr.setText(person.getTlfNr());
        email.setText(person.getEMail());
        bdag.setText(person.getBday());
        password.setText(person.getPassword());
        personRoskildeID.setText(person.getRoskildeId());
        this.person = person;
    }

    public void tIlbage(ActionEvent actionEvent) {
        stage.close();
    }

    public void gemInfo(ActionEvent actionEvent) {
        person.setNavn(navn.getText());
        person.setEfternavn(efternavn.getText());
        person.setTlfNr(tlfNr.getText());
        person.setEMail(email.getText());
        person.setBday(bdag.getText());
        person.setPassword(password.getText());
        seFriviligAnsvarligController.done();
        stage.close();
    }
}
