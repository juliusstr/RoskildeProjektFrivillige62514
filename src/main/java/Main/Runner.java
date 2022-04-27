package Main;

import Main.DatabaseLink;
import Main.GUI;

public class Runner {

    public static void main(String[] args) {
        DatabaseLink.loadPersonData("person.ser");
        DatabaseLink.loadAktivter("aktiviteter.ser");
        GUI.run();
    }
}
