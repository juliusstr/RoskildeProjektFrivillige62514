import java.util.HashMap;

public class Runner {
    public static HashMap<String, Person> personHashMap;

    public static void main(String[] args) {
        personHashMap = new HashMap<>();
        personHashMap.put("F1", new Person("Julius", "Strüwing", "40372353" , "j@s.dk", "2000-07-18", "F1","1"));
        personHashMap.put("A1", new Person("Julius", "Strüwing", "40372353" , "j@s.dk", "2000-07-18", "A1","1"));
        GUI.run();
    }
}
