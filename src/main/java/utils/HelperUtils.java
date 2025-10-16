package utils;

public class HelperUtils {


    public static String shorten(String text, int length) {
        if (text == null) return "";
        return text.length() > length ? text.substring(0, length) + "..." : text;
    }

}
