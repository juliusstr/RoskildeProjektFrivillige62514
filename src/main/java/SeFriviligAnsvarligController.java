import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;

public class SeFriviligAnsvarligController {
    private GUI gui;
    public Label currentUser;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void preeload(Person person) {
        //todo lav mig
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
}
