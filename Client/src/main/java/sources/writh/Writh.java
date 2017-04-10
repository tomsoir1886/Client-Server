package sources.writh;

import java.util.regex.Pattern;

/**
 * Created by Tomsoir on 09.04.2017.
 */
public class Writh {
    public static boolean checkInt(String s) {
        return Pattern.matches("^([0-9]*)$", s);
    }

    public static boolean checkFullName(String s) {
        return Pattern.matches("^[\\p{L} .'-]+$", s);
    }

    public static boolean checkDate(String s) {
        return Pattern.matches("^[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])$", s);
    }

    public static boolean checkDouble(String s) {
        return Pattern.matches("^([0-9]*)\\.([0-9]*)$", s);
    }

}
