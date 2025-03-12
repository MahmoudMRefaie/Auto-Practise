package org.framework;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Reporter;

public class Logger {

    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss ZZZZ";

    public static void log(String logText) {
        createLogEntry(logText);
    }

    private static void createLogEntry(String logText) {
        String timestamp = (new SimpleDateFormat(TIMESTAMP_FORMAT)).format(new Date(System.currentTimeMillis()));
        String log = timestamp + " [ReportManager] " + logText.trim();
        Reporter.log(log, true);
    }
}
