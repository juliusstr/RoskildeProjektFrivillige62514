import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FriviligHomepageController {

    private GUI gui;

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }
}
