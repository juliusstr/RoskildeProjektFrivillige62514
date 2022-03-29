import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class DatabaseLink {
    public static HashMap<String, Person> personHashMap;
    public static boolean loadPersonData(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            personHashMap = (HashMap) ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
            personHashMap.put("F1", new Person("Bob", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "F1","1"));
            personHashMap.put("A1", new Person("Bob", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "A1","1"));
            System.out.println("map.ser findes ikke. så der laves 2 nyte bruger med id F1 og A1. begge har pasword \"1\"");
            return false;
        }
        return true;
    }

    public static boolean savePersonData(String path){
        try {
            System.out.println("gemmer person data");
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(personHashMap);
            oos.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Kunne ikke gemme fil. Du er fucked");
            return false;
        }
        return true;
    }
}
