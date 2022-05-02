package Main;

import Main.Aktivitet;
import Main.DatabaseLink;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Vagt implements Serializable {
    private int id;
    private String frivillig;
    private Calendar startTidspunkt;
    private Calendar slutTidspunkt;
    private Aktivitet aktivitet;

    public Vagt(String frivillig, Calendar startTidspunkt, Calendar slutTidspunkt, Aktivitet aktivitet) {
        this.frivillig = frivillig;
        this.startTidspunkt = startTidspunkt;
        this.slutTidspunkt = slutTidspunkt;
        id = getNextId();
        this.aktivitet = aktivitet;
    }

    public String getFrivillig() {
        return frivillig;
    }


    public int getId(){
        return id;
    }

    public String getStartTidspunkt(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
        return sdf.format(startTidspunkt.getTime());
    }

    public String getSlutTidpunkt(){
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

    public String getLokation(){
        return aktivitet.getLokation();
    }

    public String getOpgave(){
        return aktivitet.getBeskrivelse();
    }

    public String getAnsvarlig(){
        return DatabaseLink.getPersonFromID(aktivitet.getAnsvarlig()).print();
    }

    public String getFriviligPerson(){
        return DatabaseLink.getPersonFromID(frivillig).print();
    }

    private int getNextId(){
        int temp = 0;
        for (int i = 0; i < DatabaseLink.aktivteter.size(); i++) {
            for (int j = 0; j < DatabaseLink.aktivteter.get(i).getVagter().size(); j++) {
                if (temp < DatabaseLink.aktivteter.get(i).getVagter().get(j).getId()){
                    temp = DatabaseLink.aktivteter.get(i).getVagter().get(j).getId();
                }
            }
        }
        temp++;
        return temp;
    }

}
