import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private String navn;
    private String efternavn;
    private String tlfNr;
    private String eMail;
    private String bDay;
    private String roskildeId;
    private String password;

    public Person(String navn, String efternavn, String tlfNr, String eMail, String bDay, String roskildeId, String password) {
        this.navn = navn;
        this.efternavn = efternavn;
        this.tlfNr = tlfNr;
        this.eMail = eMail;
        this.bDay = bDay;
        this.roskildeId = roskildeId;
        this.password = password;
    }

    public Person(Person person){
        this.navn = person.navn;
        this.efternavn = person.efternavn;
        this.tlfNr = person.tlfNr;
        this.eMail = person.eMail;
        this.bDay = person.bDay;
        this.roskildeId = person.roskildeId;
        this.password = person.password;
    }

    public String getPassword() {
        return password;
    }

    public String getRoskildeId(){
        return roskildeId;
    }

    public String getNavn() {
        return navn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public String getTlfNr() {
        return tlfNr;
    }

    public String getEMail() {
        return eMail;
    }

    public void setNavn(String text) {
        navn = text;
    }

    public void setEfternavn(String text) {
        efternavn = text;
    }

    public void setTlfNr(String text) {
        tlfNr = text;
    }

    public void setEMail(String text) {
        eMail = text;
    }

    public String print(){
        return navn + " " + efternavn + "   TLF: " + tlfNr;
    }
}
