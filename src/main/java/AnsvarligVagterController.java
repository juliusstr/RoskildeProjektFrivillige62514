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
    public ChoiceBox vagtListChoiceBox;
    private GUI gui;

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

    public void sletAktivitet(ActionEvent actionEvent) {
        if (aktivtetListeChoiceBox.getValue() != null) {
            String aktivitetId = "";
            String aktivitet = (String) aktivtetListeChoiceBox.getValue();
            aktivitetId = aktivitet.split("-")[1].substring(1);
            System.out.println(aktivitetId);
            int id = Integer.parseInt(aktivitetId);
            for (int i = 0; i < DatabaseLink.aktivteter.size(); i++) {
                if (DatabaseLink.aktivteter.get(i).getId() == id) {
                    DatabaseLink.aktivteter.remove(i);
                    break;
                }
            }
            done();
        }
    }

    /*public void setVagter(ActionEvent actionEvent) {
        if (aktivtetListeChoiceBox.getValue() != null) {
            String aktivitetId = "";
            String aktivitet = (String) aktivtetListeChoiceBox.getValue();
            aktivitetId = aktivitet.split("-")[1].substring(1);
            System.out.println(aktivitetId);
            int id = Integer.parseInt(aktivitetId);
            for (int i = 0; i < DatabaseLink.aktivteter.size(); i++) {
                if (DatabaseLink.aktivteter.get(i).getId() == id) {
                    for (int j = 0; j < DatabaseLink.aktivteter.get(i).getVagter().size(); j++) {
                        Person person = DatabaseLink.getPersonFromID(DatabaseLink.aktivteter.get(i).getVagter().get(j).getFrivillig());
                        String text = "";
                        if (person != null) {
                            text += person.getNavn() + " ";
                            text += person.getEfternavn() + " - ";
                            text += person.getRoskildeId();
                        }
                        vagtListChoiceBox.getItems().add("" + text);
                    }
                    break;
                }
            }
        } else {
            vagtListChoiceBox.getItems().clear();
        }
    }*/


    public void opretVagt(ActionEvent actionEvent) throws IOException {
        if (aktivtetListeChoiceBox.getValue() != null) {
            String aktivitetId = "";
            Aktivitet aktivitetA = null;
            String aktivitet = (String) aktivtetListeChoiceBox.getValue();
            aktivitetId = aktivitet.split("-")[1].substring(1);
            System.out.println(aktivitetId);
            int id = Integer.parseInt(aktivitetId);
            for (int i = 0; i < DatabaseLink.aktivteter.size(); i++) {
                if (DatabaseLink.aktivteter.get(i).getId() == id) {
                    aktivitetA = DatabaseLink.aktivteter.get(i);
                    break;
                }
            }
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(gui.stage);
            FXMLLoader loadrer = new FXMLLoader(getClass().getResource("VagtPopop.fxml"));
            VBox dialogVbox = loadrer.load();
            VagtPopopControler controler = loadrer.getController();
            controler.stage = dialog;
            controler.ansvarligVagterController = this;
            controler.preeload();
            controler.aktivitet = aktivitetA;
            Scene dialogScene = new Scene(dialogVbox, dialogVbox.getPrefWidth(), dialogVbox.getPrefHeight());
            dialog.setScene(dialogScene);
            dialog.setResizable(false);
            dialog.show();
        }
    }

    public void loadVagter(){
        vagtListChoiceBox.getItems().clear();
        String aktivitetId = "";
        Aktivitet aktivitetA = null;
        String aktivitet = (String) aktivtetListeChoiceBox.getValue();
        aktivitetId = aktivitet.split("-")[1].substring(1);
        int id = Integer.parseInt(aktivitetId);
        for (int i = 0; i < DatabaseLink.aktivteter.size(); i++) {
            if (DatabaseLink.aktivteter.get(i).getId() == id) {
                aktivitetA = DatabaseLink.aktivteter.get(i);
                System.out.println("titel: " + aktivitetA.getTitle());
                break;
            }
        }
        for (int i = 0; i < aktivitetA.getVagter().size(); i++) {
            vagtListChoiceBox.getItems().add("" + DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getNavn() + " " + DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getEfternavn() + " -  "+ DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getRoskildeId() + " - Start:" + aktivitetA.getVagter().get(i).printStartTidspunkt() + " Slut: " + aktivitetA.getVagter().get(i).printSlutTidpunkt());
            System.out.println(i);
            System.out.println("" + DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getNavn() + " " + DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getEfternavn() + " - Start:" + aktivitetA.getVagter().get(i).printStartTidspunkt() + " Slut: " + aktivitetA.getVagter().get(i).printSlutTidpunkt());
        }
    }

}
