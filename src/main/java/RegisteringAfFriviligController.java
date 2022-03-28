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




    public void register(MouseEvent mouseEvent) throws IOException {
        boolean bNavn = true;
        String error = "";

        if(!Pattern.matches("^[A-ZÆØÅ]{1}[a-zæøåü\\-]*", navn.getText())){
            error += "Fejl i navn\nNavn skal starte med store bogstaver, og må kun indhold alfabetiske karakter og \"-\"\n\r";
            bNavn = false;
        }

        boolean bEfternavn = true;
        if(!Pattern.matches("^[a-zA-ZæøåÆØÅü \\-]+", efterNavn.getText())){
            error += "Fejl i efternavn\nEfternavn må kun indhold alfabetiske karakter og \" \", \"-\"\n\r";
            bEfternavn = false;
        }
        boolean bTlfNr = true;
        if (!Pattern.matches("^[+]?[0-9]+", tlfNr.getText())){
            error += "Fejl i telefonnummer\nTelefonnummer må kun bestå af tal og kan indhold \"+\" efterfulgt af landekode\n\r";
            bTlfNr = false;
        }
        boolean bEMail = true;
        if (!Pattern.matches("^[A-Za-z0-9._%\\-]+[@]{1}[A-Za-z0-9.-]+[.]{1}[a-zA-Z]{2,4}", eMail.getText())){
            error += "Fejl i email\nEmail skal være gyldig email format. fx \"xxxx@xxxx.xxx\"\n\r";
            bEMail = false;
        }
        boolean bBDay = true;
        try {
            if (bDay.getValue().isAfter(LocalDate.now().minusYears(18))){
                error += "Du skal være over 18år\n\r";
                bBDay = false;
            }
        } catch (NullPointerException e){
            bBDay = false;
        }
        boolean bPassword = true;
        if (!Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}", password1.getText())) {
            error += "Fejl i password\nPassword skal indhold et stort bogstav, et småt bogstav, et tal, et special tegn og være mindst 8 karakter langt fx\"Aa1!xxxx\"\n\r";
            bPassword = false;
        }
        if (!password1.getText().equals(password2.getText())) {
            error += "Passwords er ikke ens\nBegge password felter skal indhold samme password\n\r";
            bPassword = false;
        }
        if (bNavn && bEfternavn && bTlfNr && bEMail && bBDay && bPassword) {
            System.out.println(navn.getText() + "\n" + efterNavn.getText() + "\n" + tlfNr.getText() + "\n" + eMail.getText() + "\n" + bDay.getValue().toString());
            Runner.personHashMap.put("F" + (Runner.personHashMap.size() + 1), new Person(navn.getText(), efterNavn.getText(), tlfNr.getText(), eMail.getText(), bDay.getValue().toString(), "F" + (Runner.personHashMap.size() + 1), password1.getText()));
            GUI.infoBox("Dit brugerID er:\n" + "F" + Runner.personHashMap.size(), "Du er nu registret.");
            goToLoginScene(mouseEvent);
        } else {
            password1.setText("");
            password2.setText("");
            GUI.infoBox(error,"Fejl");
            //todo informer bruger ome fejl i registering
        }
    }

    public void tilbage(MouseEvent mouseEvent) throws IOException {
        goToLoginScene(mouseEvent);
    }

    private void goToLoginScene(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loadrer = new FXMLLoader(getClass().getResource("login.fxml"));
        VBox box = loadrer.load();
        Scene login = new Scene(box,box.getPrefWidth(), box.getPrefHeight());
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();//todo spg til christian
        primaryStage.setScene(login);
    }
}
