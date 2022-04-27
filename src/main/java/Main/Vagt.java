package Main;

import Main.Aktivitet;
import Main.DatabaseLink;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Vagt implements Serializable {
    private static int nextId = 1;
    private int id;
    private String frivillig;
    private Calendar startTidspunkt;
    private Calendar slutTidspunkt;
    private Aktivitet aktivitet;

    public Vagt(String frivillig, Calendar startTidspunkt, Calendar slutTidspunkt, Aktivitet aktivitet) {
        this.frivillig = frivillig;
        this.startTidspunkt = startTidspunkt;
        this.slutTidspunkt = slutTidspunkt;
        id = nextId;
        this.aktivitet = aktivitet;
        nextId++;
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

}
