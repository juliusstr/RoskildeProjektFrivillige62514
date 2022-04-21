import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseLink {
    public static HashMap<String, Person> personHashMap;
    public static ArrayList<Aktivitet> aktivteter;

    public static boolean loadPersonData(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            personHashMap = (HashMap) ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
            personHashMap = new HashMap<>();
            personHashMap.put("F1", new Person("Bob", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "F1","1"));
            personHashMap.put("A1", new Person("Bob", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "A1","1"));
            personHashMap.put("A2", new Person("Jens", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "A2","1"));
            personHashMap.put("A3", new Person("Erik", "Jensen", "12345678" , "bob@fake.com", "1900-07-18", "A3","1"));
            System.out.println("map.ser findes ikke. så der laves 2 nyte bruger med id F1 og A1. begge har pasword \"1\"");
            return false;
        }
        return true;
    }

    public static boolean savePersonData(String path){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(personHashMap);
            oos.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Kunne ikke gemme per fil. Du er fucked");
            return false;
        }
        return true;
    }

    public static boolean loadAktivter (String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            aktivteter = (ArrayList<Aktivitet>) ois.readObject();
        } catch (Exception e){
            e.printStackTrace();
            aktivteter = new ArrayList<>();
            System.out.println("Kunne ikke loade akt fil der er nu ingen akt");
            return false;
        }
        return true;
    }

    public static boolean saveAktivetetData(String path){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(aktivteter);
            oos.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Kunne ikke gemme akt fil. Du er fucked");
            return false;
        }
        return true;
    }

    public static Person getPersonFromID(String iD){
        if (iD.equals("x")){
            return new Person("x","x","x","x","x","x","x");
        }
        for(Map.Entry<String, Person> entry : DatabaseLink.personHashMap.entrySet()) {
            String key = entry.getKey();
            if (key.equals(iD)) {
                Person person = entry.getValue();
                return person;
            }
        }
        return null;
    }

    public static ArrayList<Vagt> getVagterFraPerson(Person person){
        ArrayList<Vagt> vagter  = new ArrayList<>();
        for (int i = 0; i < aktivteter.size(); i++) {
            int n = aktivteter.get(i).getVagter().size();
            ArrayList<Vagt> aktivitetVagter = aktivteter.get(i).getVagter();
            for (int j = 0; j < n; j++) {
                if (aktivitetVagter.get(j).getFrivillig().equals(person.getRoskildeId())){
                    vagter.add(aktivitetVagter.get(j));
                }
            }
        }
        return vagter;
    }
}
