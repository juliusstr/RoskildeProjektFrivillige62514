import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class skiftPasswordPopopControler {

    public Stage stage;
    public AnsvarligMineInformationerController ansvarligMineInformationerController;
    public TextField password1;
    public TextField password2;
    private Person person;

    public void preeload(Person person) {
        this.person = person;
    }

    public void gem(ActionEvent actionEvent) {
        if (password1.getText().equals(password2.getText())){
            if (Regex.matchPassword(password1.getText())){
                person.setPassword(password1.getText());
                stage.close();
            } else {
                GUI.infoBox("Fejl i password:\n" +
                        "Password skal indhold et stort bogstav, et småt bogstav, et tal, et special tegn og være mindst 8 karakter langt. Fx.\"Aa1!xxxx\"\n" +
                        "\n", "");
            }

        } else {
            GUI.infoBox("Passwords er ikke ens:\n" +
                    "Begge password felter skal indhold samme password\n" +
                    "\n","");
        }
    }

    public void tilbage(ActionEvent actionEvent) {
        stage.close();
    }
}
