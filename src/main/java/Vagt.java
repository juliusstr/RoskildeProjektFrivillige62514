import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Vagt implements Serializable {
    private String frivillig;
    private Calendar startTidspunkt;
    private Calendar slutTidspunkt;

    public Vagt(String frivillig, Calendar startTidspunkt, Calendar slutTidspunkt) {
        this.frivillig = frivillig;
        this.startTidspunkt = startTidspunkt;
        this.slutTidspunkt = slutTidspunkt;
    }

    public String getFrivillig() {
        return frivillig;
    }

    public Calendar getStartTidspunkt() {
        return startTidspunkt;
    }

    public Calendar getSlutTidspunkt() {
        return slutTidspunkt;
    }

    public String printStartTidspunkt(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
        return sdf.format(startTidspunkt.getTime());
    }

    public String printSlutTidpunkt(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
        return sdf.format(slutTidspunkt.getTime());
    }

    public void setFrivillig(String frivillig) {
        this.frivillig = frivillig;
    }

    public void setStartTidspunkt(Calendar startTidspunkt) {
        this.startTidspunkt = startTidspunkt;
    }

    public void setSlutTidspunkt(Calendar slutTidspunkt) {
        this.slutTidspunkt = slutTidspunkt;
    }
}
