package Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
            personHashMap.put("F2", new Person("Jens", "Hansen", "87654321" , "Jens@fake.com", "1984-04-12", "F2","1"));
            personHashMap.put("A1", new Person("Bob", "Jensen", "12345678" , "bob@fake.com", "1979-08-25", "A1","1"));
            System.err.println("kunne ikke loade perosn data. Der er lavet 2");
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
            System.err.println("Kunne ikke gemme person fil.");
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
            System.err.println("Kunne ikke loade aktivtet fil.");
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
            System.err.println("Kunne ikke gemme aktivtet fil.");
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

    public static ArrayList<Vagt> getAllVagter(){
        ArrayList<Vagt> vagter  = new ArrayList<>();
        for (int i = 0; i < aktivteter.size(); i++) {
            int n = aktivteter.get(i).getVagter().size();
            ArrayList<Vagt> aktivitetVagter = aktivteter.get(i).getVagter();
            for (int j = 0; j < n; j++) {
                vagter.add(aktivitetVagter.get(j));
            }
        }
        return vagter;
    }
}
