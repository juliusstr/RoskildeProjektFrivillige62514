import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AnsvarligVagterController {
    public Label currentUser;
    public ChoiceBox aktivtetListeChoiceBox;
    public ChoiceBox vagtListChoiceBox;
    public TextArea aktivitetDisplay;
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
        DatabaseLink.aktivteter.forEach((akt) -> aktivtetListeChoiceBox.getItems().add("" + akt.getTitle() + " - " + akt.getId()));

    }

    public void preeload() {

        DatabaseLink.aktivteter.forEach((akt) -> aktivtetListeChoiceBox.getItems().add("" + akt.getTitle() + " - " + akt.getId()));
        aktivitetDisplay.setEditable(false);
    }

    public void sletAktivitet(ActionEvent actionEvent) {
        if (aktivtetListeChoiceBox.getValue() != null) {
            String aktivitetId;
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
            System.err.println("Vagt er slettet");
            done();
        }
    }

    public void opretVagt(ActionEvent actionEvent) throws IOException {
        if (aktivtetListeChoiceBox.getValue() != null) {
            String aktivitetId;
            Aktivitet aktivitetA = null;
            String aktivitet = (String) aktivtetListeChoiceBox.getValue();
            aktivitetId = aktivitet.split("-")[1].substring(1);
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
        String aktivitetId;
        Aktivitet aktivitetA = null;
        String aktivitet ="";
        try {
            aktivitet = (String) aktivtetListeChoiceBox.getValue();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        if (aktivitet.contains("-")) {
            aktivitetId = aktivitet.split("-")[1].substring(1);
            int id = Integer.parseInt(aktivitetId);
            for (int i = 0; i < DatabaseLink.aktivteter.size(); i++) {
                if (DatabaseLink.aktivteter.get(i).getId() == id) {
                    aktivitetA = DatabaseLink.aktivteter.get(i);
                    break;
                }
            }
            for (int i = 0; i < aktivitetA.getVagter().size(); i++) {
                vagtListChoiceBox.getItems().add("" + DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getNavn() + " " + DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getEfternavn() + " -  " + DatabaseLink.getPersonFromID(aktivitetA.getVagter().get(i).getFrivillig()).getRoskildeId() + " - " + (aktivitetA.getVagter().get(i)).getId() + " - Start:" + aktivitetA.getVagter().get(i).getStartTidspunkt() + " Slut: " + aktivitetA.getVagter().get(i).getSlutTidpunkt());
            }
            String temp = aktivitetA.getTitle() + "\n";
            temp += "lokation: " + aktivitetA.getLokation() + "\n";
            temp += "Beskrivelse:\n" + aktivitetA.getBeskrivelse() + "\n";
            temp += "Ansvarlig: " + aktivitetA.getAnsvarligToDisplay() + "\n";
            temp += "\nVagter:\n";

            for (int i = 0; i < vagtListChoiceBox.getItems().size(); i++) {
                temp += (String) vagtListChoiceBox.getItems().get(i) + "\n";
            }
            aktivitetDisplay.setText(temp);
        }

    }

    public void sletVagt(ActionEvent actionEvent) {
        if(vagtListChoiceBox.getValue() != null && aktivtetListeChoiceBox.getValue() != null) {
            String aktivitetId;
            int aktivitetID = 0;
            String aktivitet = (String) aktivtetListeChoiceBox.getValue();
            aktivitetId = aktivitet.split("-")[1].substring(1);
            int id = Integer.parseInt(aktivitetId);
            for (int i = 0; i < DatabaseLink.aktivteter.size(); i++) {
                if (DatabaseLink.aktivteter.get(i).getId() == id) {
                    aktivitetID = i;
                    break;
                }
            }
            String vagtIDS = ((String) vagtListChoiceBox.getValue()).split("-")[2].replace(" ","");
            int vagt =Integer.parseInt(vagtIDS);
            for (int i = 0; i < DatabaseLink.aktivteter.get(aktivitetID).getVagter().size(); i++) {
                if (DatabaseLink.aktivteter.get(aktivitetID).getVagter().get(i).getId() == vagt){
                    DatabaseLink.aktivteter.get(aktivitetID).removeVagt(vagt);
                    break;
                }
            }
            loadVagter();
        }
    }

    public void aktivtetAdmin(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setAktivtetAdminScene(person);
    }

    public void vagterAnsvarlig(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setSeMineVagterAnsvarlig(person);
    }

    public void mineInformationer(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setAnsvarligMineInformationer(person);
    }

    public void seFriviligAnsvarlig(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setSeFriviligAnsvarlig(person);
    }
}
