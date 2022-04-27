package controller;

import Main.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField brugerNavn;
    public TextField password;
    private GUI gui;

    public void clickedMouseLogin() throws IOException {
        Person person = null;
        try {
            person = DatabaseLink.personHashMap.get(brugerNavn.getText());
            if (person.getPassword().equals(password.getText())){
                if (person.getRoskildeId().charAt(0) == 'A'){
                    gui.setAnsvarligHomepageScene(person);
                } else {
                    gui.setFriviligHomepageScene(person);
                }
            } else {
                brugerNavn.setText("");
                password.setText("");
                GUI.infoBox(null, "Ugyldigt kode og eller ID");
            }
        } catch (NullPointerException e){
            brugerNavn.setText("");
            password.setText("");
            GUI.infoBox(null, "Ugyldigt kode og eller ID");
        }

    }

    public void clickedMouseRegisterSomFirvilig(MouseEvent mouseEvent) throws IOException {
        //System.err.println(this.getClass().getName());
        gui.setRegisteringAfFriviligScene();
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void keyLogin(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCharacter() == "\n") {
            Person person = null;
            try {
                person = DatabaseLink.personHashMap.get(brugerNavn.getText());
                if (person.getPassword().equals(password.getText())) {
                    if (person.getRoskildeId().charAt(0) == 'A') {
                        gui.setAnsvarligHomepageScene(person);
                    } else {
                        gui.setFriviligHomepageScene(person);
                    }
                } else {
                    brugerNavn.setText("");
                    password.setText("");
                    GUI.infoBox(null, "Ugyldigt kode og eller ID");
                }
            } catch (NullPointerException e) {
                brugerNavn.setText("");
                password.setText("");
                GUI.infoBox(null, "Ugyldigt kode og eller ID");
            }
        }
    }
}
