import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aktivitet implements Serializable {
    private String title;
    private String beskrivelse;
    private String lokation;
    private String ansvarlig;
    private int id;
    private static int nextId = 1;
    private ArrayList<Vagt> vagter;

    public Aktivitet(String title, String beskrivelse, String lokation, String ansvarlig) {
        this.title = title;
        this.beskrivelse = beskrivelse;
        this.lokation = lokation;
        this.ansvarlig = ansvarlig;
        vagter = new ArrayList<>();
        id = nextId;
        nextId++;
    }

    public String getTitle() {
        return title;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getLokation() {
        return lokation;
    }

    public String getAnsvarlig() {
        return ansvarlig;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Vagt> getVagter() {
        return (ArrayList<Vagt>) vagter.clone();
    }
}
