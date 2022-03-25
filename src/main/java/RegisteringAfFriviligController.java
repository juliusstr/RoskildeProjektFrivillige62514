import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;


public class RegisteringAfFriviligController {

    @FXML
    public TextField navn;
    public TextField efterNavn;
    public TextField tlfNr;
    public TextField eMail;
    public DatePicker bDay;
    public PasswordField password1;
    public PasswordField password2;




    public void register(MouseEvent mouseEvent) {
        if(!Pattern.matches("^[A-ZÆØÅ]{1}[a-zæøåü\\-]+", navn.getText())){
            System.out.println("fejl i navn");
        }
        if(!Pattern.matches("^[a-zA-ZæøåÆØÅü \\-]+", efterNavn.getText())){
            System.out.println("fejl i efternavn");
        }
        if (!Pattern.matches("^[+]?[0-9]+", tlfNr.getText())){
            System.out.println("fejl i tlf");
        }
        if (!Pattern.matches("^[A-Za-z0-9._%\\-]+[@]{1}[A-Za-z0-9.-]+[.]{1}[a-zA-Z]{2,4}", eMail.getText())){
            System.out.println("fejl i email");
        }
        if (bDay.getValue().isAfter(LocalDate.now().minusYears(18))){
            System.out.println("Er ikke gammel nok");
        }
        if (!Pattern.matches("^(?=.+\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,}", password1.getText())) {
            System.out.println("fejl i password1");
        }
        if (!Pattern.matches("^(?=.+\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,}", password2.getText())) {
            System.out.println("fejl i password2");
        }
        if (!password1.getText().equals(password2.getText())) {
            System.out.println("passwords er ikke ens");
        }

        System.out.println(navn.getText() + "\n" + efterNavn.getText() + "\n" + tlfNr.getText() + "\n" + eMail.getText() + "\n" + bDay.getValue().toString());
    }

    public void tilbage(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("login.fxml"));
        VBox box = loadrer.load();
        Scene registeringAfFriviligScene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();//todo spg til christian
        primaryStage.setScene(registeringAfFriviligScene);
    }
}
