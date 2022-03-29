import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FriviligHomepageController {

    public Pane pane;
    public Label currentUser;
    public Label personRoskildeID;
    private GUI gui;

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void setFriviligeInformationerScene(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setFriviligeInformationerScene(person);
    }
}
