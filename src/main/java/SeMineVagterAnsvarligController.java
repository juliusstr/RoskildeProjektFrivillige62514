import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;


import java.io.IOException;
import java.util.ArrayList;

public class SeMineVagterAnsvarligController {
    public Label currentUser;
    public TableView vagtListe;
    public TextField soegeBar;
    private GUI gui;
    private Person person;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void preeload(Person person) {
        this.person = person;
        vagtListe.getColumns().clear();
        vagtListe.setEditable(false);

        TableColumn<Person, String> lokationColumn = new TableColumn<>("Lokation");
        lokationColumn.setCellValueFactory(
                new PropertyValueFactory<>("lokation"));

        vagtListe.getColumns().addAll(lokationColumn);

        TableColumn<Person, String> startColumn = new TableColumn<>("Vagt start");
        startColumn.setCellValueFactory(
                new PropertyValueFactory<>("startTidspunkt"));

        vagtListe.getColumns().addAll(startColumn);

        TableColumn<Person, String> slutColumn = new TableColumn<>("Vagt Slut");
        slutColumn.setCellValueFactory(
                new PropertyValueFactory<>("slutTidpunkt"));

        vagtListe.getColumns().addAll(slutColumn);

        TableColumn<Person, String> opgaveColumn = new TableColumn<>("Opgave");
        opgaveColumn.setCellValueFactory(
                new PropertyValueFactory<>("opgave"));

        vagtListe.getColumns().addAll(opgaveColumn);

        TableColumn<Person, String> friviligColumn = new TableColumn<>("Frivilig");
        friviligColumn.setCellValueFactory(
                new PropertyValueFactory<>("friviligPerson"));

        vagtListe.getColumns().addAll(friviligColumn);

        TableColumn<Person, String> ansvarligColumn = new TableColumn<>("Ansvarlig");
        ansvarligColumn.setCellValueFactory(
                new PropertyValueFactory<>("ansvarlig"));

        vagtListe.getColumns().addAll(ansvarligColumn);

        vagtListe.setPlaceholder( new Label("Der er ingen vager at se her.\nPrøv evt. at søge på noget andet."));

        ArrayList<Vagt> vagter = DatabaseLink.getAllVagter();
        System.out.println("der er " + vagter.size() + " vagter");
        for (int i = 0; i < vagter.size(); i++) {
            vagtListe.getItems().add(vagter.get(i));
        }



    }

    public void logud(ActionEvent actionEvent) throws IOException {
        gui.setLoginScene();
    }

    public void setFriviligeInformationerScene(ActionEvent actionEvent) throws IOException {
        gui.setFriviligeInformationerScene(person);
    }

    public void soeg() {
        ArrayList<Vagt> vagter = DatabaseLink.getAllVagter();
        vagter.removeIf((e) -> !(e.getLokation().contains(soegeBar.getText()) || e.getStartTidspunkt().contains(soegeBar.getText()) || e.getSlutTidpunkt().contains(soegeBar.getText()) || e.getOpgave().contains(soegeBar.getText()) || e.getAnsvarlig().contains(soegeBar.getText()) || e.getFriviligPerson().contains(soegeBar.getText())));
        vagtListe.getItems().clear();
        for (int i = 0; i < vagter.size(); i++) {
            vagtListe.getItems().add(vagter.get(i));
        }

    }

    public void aktivtetAdmin(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setAktivtetAdminScene(person);
    }

    public void vagterAnsvarlig(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setSeMineVagterAnsvarlig(person);
    }

    public void mineInformationer(ActionEvent actionEvent) throws IOException {
        Person person = DatabaseLink.personHashMap.get(currentUser.getText().split(" ")[0]);
        gui.setAnsvarligMineInformationer(person);
    }
}
