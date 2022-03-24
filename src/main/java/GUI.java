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

public class GUI extends Application {


    public static void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("FriviligHomepage.fxml"));
        VBox box = loadrer.load();
        // log ind side baner
        /*GridPane gridPaneLogniSide = new GridPane();
        GridPane bannerpane = new GridPane();
        Label topBanner = new Label("Roskilde Festivalg 2022");
        Label loginInfo = new Label("xxxxxxxxxxxxx");
        AnchorPane loginAncorPane = new AnchorPane();
        AnchorPane topBannerAncorPane = new AnchorPane();
        loginAncorPane.getChildren().addAll(loginInfo);
        AnchorPane.setLeftAnchor(loginInfo, 100.0);
        topBannerAncorPane.getChildren().addAll(topBanner);
        AnchorPane.setRightAnchor(topBanner, 100.0);
        bannerpane.add(topBannerAncorPane,1,1);
        bannerpane.add(loginAncorPane, 2,1);
        gridPaneLogniSide.add(bannerpane,1,1);
        // log ind side login
        HBox loginDataHbox = new HBox();
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        separator1.setMinWidth(110.0);
        VBox loginDataVbox = new VBox();

        Label loginBrugerNanvLabel = new Label("Brugernavn");
        TextField loginBrugerNavnTextField = new TextField();

        loginDataVbox.getChildren().addAll(loginBrugerNanvLabel,loginBrugerNavnTextField);

        loginDataHbox.getChildren().addAll(separator1,loginDataVbox,separator2);

        gridPaneLogniSide.add(loginDataHbox, 1,2);

        Parent root = FXMLLoader.load();

        //Scene logniSide = new Scene(gridPaneLogniSide, 400,400);*/
        Scene logniSide = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(logniSide);
        stage.setResizable(false);
        stage.show();

    }
}
