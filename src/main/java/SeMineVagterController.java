import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SeMineVagterController {

    public ListView vagtListe;
    public TextField siegeBar;
    private GUI gui;
    private Person person;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void preeload(Person person) {
        this.person = person;
        DatabaseLink.getVagterFraPerson(person);
        //vagtListe.getItems().add()
    }

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void setFriviligeInformationerScene(ActionEvent actionEvent) throws IOException {
        gui.setFriviligeInformationerScene(person);
    }

    public void soeg(ActionEvent actionEvent) {
        //todo lav søge funktion
    }
}
