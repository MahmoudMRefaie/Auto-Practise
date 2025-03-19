package utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";

    private AllureUtils() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void attachLogsToAllureReport() {
        try {
            File logFile = FilesUtils.getLatestFile(ReportManager.LOGS_RESULTS_PATH);
            if (!logFile.exists()) {
                ReportManager.warn("Log file does not exist: ", logFile.toString());
                return;
            }
            Allure.addAttachment("Logs.log", Files.readString(Path.of(logFile.getPath())));
            ReportManager.info("Logs are attached to allure report");
        } catch (Exception e) {
            ReportManager.error("Failed to attach logs to Allure report: ", e.getMessage());
        }
    }

    public static void addScreenshotToAllure(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
            ReportManager.info("Screenshot is attached to allure report");
        } catch (Exception e) {
            ReportManager.error("Failed to attach screenshot to Allure report: ", e.getMessage());
        }
    }
}
