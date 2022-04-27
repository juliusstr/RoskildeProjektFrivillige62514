package controller.frivilig;

import Main.*;
import controller.frivilig.popop.FriviligFskiftPasswordPopopControler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FriviligMineInformationerController {
    public TextField navn;
    public TextField efternavn;
    public TextField tlfNr;
    public TextField email;
    public Label personRoskildeID;
    public Button gemBnt;
    public Button redigerBtn;
    public Button passwordBtn;
    public Label currentUser;
    public Label bdag;
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
        bdag.setText(person.getBday());

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

    public void gemInfo(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(personRoskildeID.getText().split(" ")[0]);
        boolean bNavn  = true;
        if (!Regex.matchNavn(navn.getText())){
            bNavn = false;
        }
        boolean bEfternavn = true;
        if (!Regex.matchEfterNavn(efternavn.getText())){
            bEfternavn = false;
        }
        boolean bTlfNr = true;
        if (!Regex.matchTlfNr(tlfNr.getText())){
            bTlfNr = false;
        }
        boolean bEMail = true;
        if (!Regex.matchEMail(email.getText())){
            bEMail = false;
        }

        if (bNavn && bEfternavn && bTlfNr && bEMail){
            person.setNavn(navn.getText());
            person.setEfternavn(efternavn.getText());
            person.setTlfNr(tlfNr.getText());
            person.setEMail(email.getText());
            gui.setFriviligeInformationerScene(person);
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

    public void seMineVagter(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setfriviligMineVagterScene(person);
    }

    public void skiftPassword(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gui.stage);
        FXMLLoader loadrer = new FXMLLoader(getClass().getClassLoader().getResource(GUI.friviligPopopSkiftPassword));
        AnchorPane dialogVbox = loadrer.load();
        FriviligFskiftPasswordPopopControler controler = loadrer.getController();
        controler.stage = dialog;
        controler.friviligMineInformationerController = this;
        controler.preeload(person);
        Scene dialogScene = new Scene(dialogVbox, dialogVbox.getPrefWidth(), dialogVbox.getPrefHeight());
        dialog.setScene(dialogScene);
        dialog.setResizable(false);
        dialog.show();
    }
}
