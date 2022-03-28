import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField brugerNavn;
    public TextField password;

    public void clickedMouseLogin(MouseEvent mouseEvent) throws IOException {
        Person person = null;
        try {
            person = Runner.personHashMap.get(brugerNavn.getText());
            if (person.getPassword().equals(password.getText())){
                if (person.getPassword().charAt(0) == 'A'){
                    // todo login som ansvarlig
                } else {
                    FXMLLoader loadrer = new FXMLLoader(getClass().getResource("FriviligHomepage.fxml"));
                    VBox box = loadrer.load();
                    ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
                    Scene friviligHomePage = new Scene(box, box.getPrefWidth(), box.getPrefHeight());
                    Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();//todo spg til christian
                    primaryStage.setScene(friviligHomePage);
                }
            } else {

            }
        } catch (NullPointerException e){
            e.printStackTrace();
            //todo prompt bruger for nyt brugernanv og password da de ikke matcher
        }

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
