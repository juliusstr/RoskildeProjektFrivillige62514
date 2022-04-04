import java.io.Serializable;
import java.util.List;

public class Aktivitet implements Serializable {
    private String title;
    private String beskrivelse;
    private String lokation;
    private String ansvarlig;
    private List<Vagt> vagter;
}
