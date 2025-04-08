package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private AlertActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    private static Alert getAlert(DriverManager driver) {
        return Waits.waitForAlertToBePresent(driver);
//        return driver.switchTo().alert();
    }

    @Step("Accepting alert")
    public static void acceptAlert(DriverManager driver) {
        ReportManager.info("Alert to be accepted: ", driver.get().getCurrentUrl());
        getAlert(driver).accept();
    }

    @Step("Dismissing alert")
    public static void dismissAlert(DriverManager driver) {
        ReportManager.info("Alert to be dismissed: ", driver.get().getCurrentUrl());
        getAlert(driver).dismiss();
    }

    @Step("Getting alert text")
    public static String getAlertText(DriverManager driver) {
        ReportManager.info("Getting alter text: ", driver.get().getCurrentUrl());
        return getAlert(driver).getText();
    }

    @Step("Sending text to alert: {text}")
    public static void sendTextToAlert(DriverManager driver, String text) {
        ReportManager.info("Sending text to alert: ", driver.get().getCurrentUrl(), " text: ", text);
        getAlert(driver).sendKeys(text);
    }
}
