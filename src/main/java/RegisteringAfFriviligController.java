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
import java.util.Date;
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
        boolean bNavn = true;
        if(!Pattern.matches("^[A-ZÆØÅ]{1}[a-zæøåü\\-]+", navn.getText())){
            System.out.println("fejl i navn");
            bNavn = false;
        }

        boolean bEfternavn = true;
        if(!Pattern.matches("^[a-zA-ZæøåÆØÅü \\-]+", efterNavn.getText())){
            System.out.println("fejl i efternavn");
            bEfternavn = false;
        }
        boolean bTlfNr = true;
        if (!Pattern.matches("^[+]?[0-9]+", tlfNr.getText())){
            System.out.println("fejl i tlf");
            bTlfNr = false;
        }
        boolean bEMail = true;
        if (!Pattern.matches("^[A-Za-z0-9._%\\-]+[@]{1}[A-Za-z0-9.-]+[.]{1}[a-zA-Z]{2,4}", eMail.getText())){
            System.out.println("fejl i email");
            bEMail = false;
        }
        boolean bBDay = true;
        try {
            if (bDay.getValue().isAfter(LocalDate.now().minusYears(18))){
                System.out.println("Er ikke gammel nok");
                bBDay = false;
            }
        } catch (NullPointerException e){
            bBDay = false;
        }
        boolean bPassword = true;
        if (!Pattern.matches("^(?=.+\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,}", password1.getText())) {
            System.out.println("fejl i password1");
            bPassword = false;
        }
        if (!Pattern.matches("^(?=.+\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,}", password2.getText())) {
            System.out.println("fejl i password2");
            bPassword = false;
        }
        if (!password1.getText().equals(password2.getText())) {
            System.out.println("passwords er ikke ens");
            bPassword = false;
        }
        if (bNavn && bEfternavn && bTlfNr && bEMail && bBDay && bPassword) {
            System.out.println(navn.getText() + "\n" + efterNavn.getText() + "\n" + tlfNr.getText() + "\n" + eMail.getText() + "\n" + bDay.getValue().toString());
            Runner.personHashMap.put(("" + (Runner.personHashMap.size() + 1)), new Person(navn.getText(), efterNavn.getText(), tlfNr.getText(), eMail.getText(), new Date(bDay.getValue().toString()), "" + Runner.personHashMap.size() + 1, password1.getText()));
        }
    }

    public void tilbage(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("login.fxml"));
        VBox box = loadrer.load();
        Scene registeringAfFriviligScene = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();//todo spg til christian
        primaryStage.setScene(registeringAfFriviligScene);
    }
}
