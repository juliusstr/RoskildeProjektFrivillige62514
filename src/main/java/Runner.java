import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Runner {
    public static HashMap<String, Person> personHashMap;

    public static void main(String[] args) {
        personHashMap = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("person.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            personHashMap = (HashMap) ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
            personHashMap.put("F1", new Person("Bob", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "F1","1"));
            personHashMap.put("A1", new Person("Bob", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "A1","1"));
            System.out.println("map.ser findes ikke. så der laves 2 nyte bruger med id F1 og A1. begge har pasword \"1\"");
        }
        GUI.run();
    }
}
