import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aktivitet implements Serializable {
    private String title;
    private String beskrivelse;
    private String lokation;
    private String ansvarlig;
    private ArrayList<Vagt> vagter;

    public Aktivitet(String title, String beskrivelse, String lokation, String ansvarlig) {
        this.title = title;
        this.beskrivelse = beskrivelse;
        this.lokation = lokation;
        this.ansvarlig = ansvarlig;
        vagter = new ArrayList<>();
    }
}
