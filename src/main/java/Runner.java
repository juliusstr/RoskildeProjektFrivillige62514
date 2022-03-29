import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Runner {

    public static void main(String[] args) {
        DatabaseLink.loadPersonData("person.ser");
        GUI.run();
    }
}
