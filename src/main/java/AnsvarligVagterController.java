import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class AnsvarligVagterController {
    public ChoiceBox aktivtetListeChoiceBox;
    private GUI gui;
    public Aktivitet newAktivitet;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void newAktivtet(ActionEvent actionEvent) throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gui.stage);
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("AktivitetPopop.fxml"));
        VBox dialogVbox = loadrer.load();
        AktivitetPopopControler controler = loadrer.getController();
        controler.newAktivitet = newAktivitet;
        controler.stage = dialog;
        controler.ansvarligVagterController = this;
        controler.preeload();
        Scene dialogScene = new Scene(dialogVbox, dialogVbox.getPrefWidth(), dialogVbox.getPrefHeight());
        dialog.setScene(dialogScene);
        dialog.setResizable(false);
        dialog.show();
    }

    public void done() {
        aktivtetListeChoiceBox.getItems().clear();
        DatabaseLink.aktivteter.forEach((akt) ->{
            aktivtetListeChoiceBox.getItems().add("" + akt.getTitle() + " - " + akt.getId());
        });
    }

    public void preeload() {
        DatabaseLink.aktivteter.forEach((akt) ->{
        aktivtetListeChoiceBox.getItems().add("" + akt.getTitle() + " - " + akt.getId());
        });
    }
}
