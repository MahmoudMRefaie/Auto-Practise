package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {

    private TimestampUtils() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static String getTimestamp() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS");
        return formatter.format(date);
    }
}
