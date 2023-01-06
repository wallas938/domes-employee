package fr.greta.domes.utils;

public class Utils {

    public static int intParser(String value) {
        int i = 1;
        try {
            i = Integer.parseInt(value);
            return i;
        } catch (NumberFormatException e) {
            i = 1;
            return i;
        }
    }

    public static double doubleParser(String value) {
        int d = 1;
        try {
            d = Integer.parseInt(value);
            return d;
        } catch (NumberFormatException e) {
            d = 1;
            return d;
        }
    }
}
