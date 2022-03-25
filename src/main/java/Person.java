import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private String navn;
    private String efternavn;
    private String tlfNr;
    private String eMail;
    private Date bDay;
    private String roskildeId;
    private String password;

    public Person(String navn, String efternavn, String tlfNr, String eMail, Date bDay, String roskildeId, String password) {
        this.navn = navn;
        this.efternavn = efternavn;
        this.tlfNr = tlfNr;
        this.eMail = eMail;
        this.bDay = bDay;
        this.roskildeId = roskildeId;
        this.password = password;
    }
}
