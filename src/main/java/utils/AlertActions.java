package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private AlertActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    private static Alert getAlert(WebDriver driver) {
        return Waits.waitForAlertToBePresent(driver);
        //return driver.switchTo().alert();
    }

    public static void acceptAlert(WebDriver driver) {
        getAlert(driver).accept();
    }

    public static void dismissAlert(WebDriver driver) {
        getAlert(driver).dismiss();
    }

    public static String getAlertText(WebDriver driver) {
        return getAlert(driver).getText();
    }

    public static void sendTextToAlert(WebDriver driver, String text) {
        getAlert(driver).sendKeys(text);
    }
}
