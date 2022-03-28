import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AnsvarligHomepsgeController {
    private GUI gui;

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }


}
