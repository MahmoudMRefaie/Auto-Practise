package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private AlertActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    private static Alert getAlert(WebDriver driver) {
        return Waits.waitForAlertToBePresent(driver);
//        return driver.switchTo().alert();
    }

    @Step("Accepting alert")
    public static void acceptAlert(WebDriver driver) {
        ReportManager.info("Alert to be accepted: ", driver.getCurrentUrl());
        getAlert(driver).accept();
    }

    @Step("Dismissing alert")
    public static void dismissAlert(WebDriver driver) {
        ReportManager.info("Alert to be dismissed: ", driver.getCurrentUrl());
        getAlert(driver).dismiss();
    }

    @Step("Getting alert text")
    public static String getAlertText(WebDriver driver) {
        ReportManager.info("Getting alter text: ", driver.getCurrentUrl());
        return getAlert(driver).getText();
    }

    @Step("Sending text to alert: {text}")
    public static void sendTextToAlert(WebDriver driver, String text) {
        ReportManager.info("Sending text to alert: ", driver.getCurrentUrl(), " text: ", text);
        getAlert(driver).sendKeys(text);
    }
}
