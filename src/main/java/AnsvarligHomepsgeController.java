import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AnsvarligHomepsgeController {
    public Label currentUser;
    private GUI gui;

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }


    public void mineInformationer(ActionEvent actionEvent) throws IOException {//todo skift scene
        Person person = Runner.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setAnsvarligMineInformationer(person);
    }
}
