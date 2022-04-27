package Main;

import controller.*;

import controller.ansvarlig.*;
import controller.frivilig.FriviligHomepageController;
import controller.frivilig.FriviligMineInformationerController;
import controller.RegisteringAfFriviligController;
import controller.frivilig.SeMineVagterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.*;

//todo lave fil navne som konstanter

//todo flyt fxml til mapper så det er nemmer at fidne rundt mellem frivilige og ansvarlige

public class GUI extends Application {

    public static final String personMapPath = "Main.Person.map";
    public static final String aktivitetListPath = "Aktivtet.list";

    public static final String loginPath = "login.fxml";

    public static final String FriviligHomepagePath = "Frivilig/FriviligHomepage.fxml";
    public static final String AnsvarligHomepagePath = "Ansvarlig/AnsvarligHomepage.fxml";
    public static final String FriviligMineInformationerPath= "Frivilig/FriviligMineInformationer.fxml";
    public static final String AnsvarligMineInformationerPath = "Ansvarlig/AnsvarligMineInformationer.fxml";
    public static final String AnsvarligVagterPath = "Ansvarlig/AnsvarligVagter.fxml";
    public static final String FriviligSeMineVagterPath = "Frivilig/SeMineVagter.fxml";
    public static final String AnsvarligSeMineVagterPath = "Ansvarlig/SeMineVagterAnsvarlig.fxml";
    public static final String AnsvarligRedigereFriviligePath = "Ansvarlig/AnsvarligRedigereFrivilige.fxml";

    public static final String registeringAfFriviligPath = "RegisteringAfFrivilig.fxml";

    public static final String ansvarligPopopNyAktivitetPath = "Ansvarlig/popop/AktivitetPopop.fxml";
    public static final String ansvarligPopopNyVagt = "Ansvarlig/popop/VagtPopop.fxml";
    public static final String ansvarligPopopRedigerFriviligInformationer = "Ansvarlig/popop/AnsvarligRedigerFriviligInformationerPopop.fxml";
    public static final String ansvarligPopopSkiftPassword = "Ansvarlig/popop/SkiftPasswordPopop.fxml";

    public static final String friviligPopopSkiftPassword = "Frivilig/popop/FriviligSkiftPasswordPopop.fxml";

    public Stage stage;

    public static void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;

        FXMLLoader loadrer = new FXMLLoader(getClass().getClassLoader().getResource(loginPath));
        VBox box = loadrer.load();
        Scene logniSide = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        LoginController loginController = loadrer.getController();
        loginController.setGUI(this);
        stage.setScene(logniSide);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            DatabaseLink.savePersonData(personMapPath);
            DatabaseLink.saveAktivetetData(aktivitetListPath);
        });
        stage.show();
    }

    public static void infoBox(String infoMessage, String titleBar) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(titleBar);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public void setRegisteringAfFriviligScene() throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getClassLoader().getResource(registeringAfFriviligPath));
        VBox box = loadrer.load();
        RegisteringAfFriviligController registeringAfFriviligController = loadrer.getController();
        registeringAfFriviligController.setGUI(this);
        Scene registeringAfFriviligScene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(registeringAfFriviligScene);
    }

    public void setFriviligHomepageScene(Person person) throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getClassLoader().getResource(FriviligHomepagePath));
        VBox box = loadrer.load();
        FriviligHomepageController friviligHomepageController = loadrer.getController();
        friviligHomepageController.setGui(this);
        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        Scene friviligHomePage = new Scene(box, box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(friviligHomePage);
    }

    public void setLoginScene() throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getClassLoader().getResource(loginPath));
        VBox box = loadrer.load();
        LoginController loginController = loadrer.getController();
        loginController.setGUI(this);
        Scene login = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(login);
    }

    public void setAnsvarligHomepageScene(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(AnsvarligHomepagePath));
        VBox box = loader.load();
        AnsvarligHomepsgeController ansvarligHomepsgeController = loader.getController();
        ansvarligHomepsgeController.setGUI(this);
        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        Scene ansvarligHomepageScene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(ansvarligHomepageScene);
    }

    public void setFriviligeInformationerScene(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FriviligMineInformationerPath));
        VBox box = loader.load();
        FriviligMineInformationerController friviligMineInformationerController = loader.getController();
        friviligMineInformationerController.setGUI(this);
        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        friviligMineInformationerController.preLoad(person);
        Scene friviligMineInformationerScene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(friviligMineInformationerScene);
    }


    public void setAnsvarligMineInformationer(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(AnsvarligMineInformationerPath));
        VBox box = loader.load();
        AnsvarligMineInformationerController controller = loader.getController();
        controller.setGUI(this);
        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        controller.preLoad(person);
        Scene scene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(scene);
    }

    public void setAktivtetAdminScene(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(AnsvarligVagterPath));
        VBox box = loader.load();
        AnsvarligVagterController controller = loader.getController();
        controller.setGUI(this);
        controller.preeload();
        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        Scene scene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(scene);
    }

    public void setfriviligMineVagterScene(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FriviligSeMineVagterPath));
        VBox box = loader.load();
        SeMineVagterController controller = loader.getController();

        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        Scene scene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(scene);

        controller.setGUI(this);
        controller.preeload(person);
    }

    public void setSeMineVagterAnsvarlig(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(AnsvarligSeMineVagterPath));
        VBox box = loader.load();
        SeMineVagterAnsvarligController controller = loader.getController();

        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        Scene scene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(scene);

        controller.setGUI(this);
        controller.preeload(person);
    }

    public void setSeFriviligAnsvarlig(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(AnsvarligRedigereFriviligePath));
        VBox box = loader.load();
        SeFriviligAnsvarligController controller = loader.getController();

        ((Label) ((HBox) box.getChildren().get(0)).getChildren().get(2)).setText(person.getRoskildeId() + " - " + person.getNavn() + " " + person.getEfternavn());
        Scene scene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        stage.setScene(scene);

        controller.setGUI(this);
        controller.preeload(person);
    }
}
