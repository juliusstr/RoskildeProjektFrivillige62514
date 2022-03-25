import java.util.HashMap;

public class Runner {
    public static HashMap<String, Person> personHashMap;

    public static void main(String[] args) {
        personHashMap = new HashMap<>();
        GUI.run();

    }
}
