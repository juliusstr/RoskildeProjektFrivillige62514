import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class GUI extends Application {
    public HashMap<String, Person> personHashMap;


    public static void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        personHashMap = new HashMap<>();
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("login.fxml"));
        VBox box = loadrer.load();
        Scene logniSide = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(logniSide);
        stage.setResizable(false);
        stage.show();


    }
}
