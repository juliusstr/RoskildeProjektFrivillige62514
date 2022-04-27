package controller.ansvarlig;

import Main.*;
import controller.ansvarlig.popop.AnsvarligRedigerFriviligInformationerPopopController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SeFriviligAnsvarligController {
    public ListView friviligListe;
    public Button rediger;
    public TextField sogeBar;
    private GUI gui;
    public Label currentUser;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void preeload(Person person) {

        DatabaseLink.personHashMap.forEach((k, v) ->
                friviligListe.getItems().add("" + v.getRoskildeId() + " - " + v.getNavn() + " " + v.getEfternavn() + "   TLF: " + v.getTlfNr() + "   Email: " + v.getEMail())
                );
    }

    public void mineInformationer(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setAnsvarligMineInformationer(person);
    }


    public void aktivtetAdmin(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setAktivtetAdminScene(person);
    }

    public void vagterAnsvarlig(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setSeMineVagterAnsvarlig(person);
    }

    public void seFriviligAnsvarlig(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setSeFriviligAnsvarlig(person);
    }

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void rediger(ActionEvent actionEvent) throws IOException {
        if (friviligListe.getSelectionModel().getSelectedIndex() != -1) {
            String id = (String) friviligListe.getItems().get(friviligListe.getSelectionModel().getSelectedIndex());
            id = id.split(" - ")[0];
            Person person = DatabaseLink.getPersonFromID(id);

            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(gui.stage);
            FXMLLoader loadrer = new FXMLLoader(getClass().getClassLoader().getResource(GUI.ansvarligPopopRedigerFriviligInformationer));
            VBox dialogVbox = loadrer.load();
            AnsvarligRedigerFriviligInformationerPopopController controler = loadrer.getController();
            controler.stage = dialog;
            controler.seFriviligAnsvarligController = this;
            controler.preeload(person);
            Scene dialogScene = new Scene(dialogVbox, dialogVbox.getPrefWidth(), dialogVbox.getPrefHeight());
            dialog.setScene(dialogScene);
            dialog.setResizable(false);
            dialog.show();
        }
    }

    public void soege() {
        ArrayList<Person> personer = new ArrayList<>();
        DatabaseLink.personHashMap.forEach((v,k) -> personer.add(k));
        personer.removeIf((e) -> !(e.getRoskildeId().contains(sogeBar.getText()) || e.getNavn().contains(sogeBar.getText()) || e.getEfternavn().contains(sogeBar.getText()) || e.getTlfNr().contains(sogeBar.getText()) || e.getEMail().contains(sogeBar.getText())));
        friviligListe.getItems().clear();
        for (int i = 0; i < personer.size(); i++) {
            Person v = personer.get(i);
            friviligListe.getItems().add("" + v.getRoskildeId() + " - " + v.getNavn() + " " + v.getEfternavn() + "   TLF: " + v.getTlfNr() + "   Email: " + v.getEMail());
        }

    }

    public void done() {
        sogeBar.setText("");
        ArrayList<Person> personer = new ArrayList<>();
        DatabaseLink.personHashMap.forEach((v,k) -> personer.add(k));
        personer.removeIf((e) -> !(e.getRoskildeId().contains(sogeBar.getText()) || e.getNavn().contains(sogeBar.getText()) || e.getEfternavn().contains(sogeBar.getText()) || e.getTlfNr().contains(sogeBar.getText()) || e.getEMail().contains(sogeBar.getText())));
        friviligListe.getItems().clear();
        for (int i = 0; i < personer.size(); i++) {
            Person v = personer.get(i);
            friviligListe.getItems().add("" + v.getRoskildeId() + " - " + v.getNavn() + " " + v.getEfternavn() + "   TLF: " + v.getTlfNr() + "   Email: " + v.getEMail());
        }
    }
}
