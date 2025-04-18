package utils;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.framework.PropertiesUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotsUtils {

    private static final String SCREENSHOTS_PATH = "test-outputs/screenshots/";

    private ScreenshotsUtils() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + ".png");
            FileUtils.copyFile(screenshot, screenshotFile);
            AllureUtils.addScreenshotToAllure(screenshotName, screenshotFile.getPath());
        } catch (IOException e) {
            ReportManager.error("Failed to take a screenshot", e.getMessage());
        }
    }
}
