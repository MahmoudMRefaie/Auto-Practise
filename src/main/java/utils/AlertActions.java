package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertActions {

    private final WebDriver driver;
    private final Waits wait;
    private final BrowserActions browserActions;

    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new Waits(driver);
        this.browserActions = new BrowserActions(driver);
    }

    private Alert getAlert() {
        return wait.waitForAlertToBePresent();
//        return driver.switchTo().alert();
    }

    @Step("Accepting alert")
    public void acceptAlert() {
        ReportManager.info("Alert to be accepted: ", browserActions.getCurrentURL());
        getAlert().accept();
    }

    @Step("Dismissing alert")
    public void dismissAlert() {
        ReportManager.info("Alert to be dismissed: ", browserActions.getCurrentURL());
        getAlert().dismiss();
    }

    @Step("Getting alert text")
    public String getAlertText() {
        ReportManager.info("Getting alter text: ", browserActions.getCurrentURL());
        return getAlert().getText();
    }

    @Step("Sending text to alert: {text}")
    public void sendTextToAlert(String text) {
        ReportManager.info("Sending text to alert: ", browserActions.getCurrentURL(), " text: ", text);
        getAlert().sendKeys(text);
    }
}
