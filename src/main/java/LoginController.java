import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    public Label label;

    public void clickedMouse(MouseEvent mouseEvent) {
        label.setText("UHA");
    }
}
