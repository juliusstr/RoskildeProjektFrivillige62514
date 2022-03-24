import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        System.out.println(navn.getText() + "\n" + efterNavn.getText() + "\n" + tlfNr.getText() + "\n" + eMail.getText());
    }
}
