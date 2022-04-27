import java.util.regex.Pattern;

public class Regex {
    public static final String navnRegex = "^[A-ZÆØÅ]{1}[a-zæøåü\\-]*";
    public static final String efternavnRegex = "^[a-zA-ZæøåÆØÅü \\-]+";
    public static final String tlfNrRegex = "^[+]?[0-9]+";
    public static final String eMailRegex = "^[A-Za-z0-9._%\\-]+[@]{1}[A-Za-z0-9.-]+[.]{1}[a-zA-Z]{2,4}";
    public static final String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}";


    public static boolean matchNavn(String text){
        return Pattern.matches(navnRegex, text);
    }

    public static boolean matchEfterNavn(String text){
        return Pattern.matches(efternavnRegex, text);
    }

    public static boolean matchTlfNr(String text){
        return Pattern.matches(tlfNrRegex, text);
    }

    public static boolean matchEMail(String text){
        return Pattern.matches(eMailRegex, text);
    }

    public static boolean matchPassword(String text){
        return Pattern.matches(passwordRegex, text);
    }
}
