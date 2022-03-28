import javafx.event.ActionEvent;

import java.io.IOException;

public class FriviligMineInformationerController {
    private GUI gui;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }
}
