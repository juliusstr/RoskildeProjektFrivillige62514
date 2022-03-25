import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public Label label;

    public void clickedMouseLogin(MouseEvent mouseEvent) {
        label.setText("UHA");
    }

    public void clickedMouseRegisterSomFirvilig(MouseEvent mouseEvent) throws IOException {
        //System.err.println(this.getClass().getName());
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("RegisteringAfFrivilig.fxml"));
        VBox box = loadrer.load();
        Scene registeringAfFriviligScene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();//todo spg til christian
        primaryStage.setScene(registeringAfFriviligScene);
    }
}
