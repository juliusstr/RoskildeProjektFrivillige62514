import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class GUI extends Application {


    private Stage stage;

    public static void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("login.fxml"));
        VBox box = loadrer.load();
        Scene logniSide = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        LoginController loginController = loadrer.getController();
        loginController.setGUI(this);
        stage.setScene(logniSide);
        stage.setResizable(false);
        stage.show();
    }

    public static void infoBox(String infoMessage, String titleBar) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle(titleBar);
        alert.setHeaderText(titleBar);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public void setRegisteringAfFriviligScene() throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("RegisteringAfFrivilig.fxml"));
        VBox box = loadrer.load();
        RegisteringAfFriviligController registeringAfFriviligController = loadrer.getController();
        registeringAfFriviligController.setGUI(this);
        Scene registeringAfFriviligScene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(registeringAfFriviligScene);
    }

    public void setFriviligHomepageScene(Person person) throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("FriviligHomepage.fxml"));
        VBox box = loadrer.load();
        FriviligHomepageController friviligHomepageController = loadrer.getController();
        friviligHomepageController.setGui(this);
        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        Scene friviligHomePage = new Scene(box, box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(friviligHomePage);
    }

    public void setLoginScene() throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("Login.fxml"));
        VBox box = loadrer.load();
        LoginController loginController = loadrer.getController();
        loginController.setGUI(this);
        Scene login = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(login);
    }

}
