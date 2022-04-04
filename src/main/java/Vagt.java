import java.io.Serializable;

public class Vagt implements Serializable {
    private String frivillig;
    private String startTidspunkt;
    private String slutTidspunkt;

    public String getFrivillig() {
        return frivillig;
    }

    public String getStartTidspunkt() {
        return startTidspunkt;
    }

    public String getSlutTidspunkt() {
        return slutTidspunkt;
    }
}
