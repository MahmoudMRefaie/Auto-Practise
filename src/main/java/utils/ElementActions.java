package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ElementActions {

    private final WebDriver driver;
    private final Waits wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        wait = new Waits(driver);
    }

    @Step("Sending text: {text} to the element: {locator}")
    public void sendKeys(By locator, String text){

        clear(locator);
        ReportManager.info("Sending text: ", text, " to the element: ", locator.toString());
        findElement(locator).sendKeys(text);
    }

    @Step("Sending append text: {text} to the element: {locator}")
    public void sendKeysAppend(By locator, String text){

        wait.waitForElementToBeVisible(locator);
        scrollToElement(locator);
        ReportManager.info("Sending text: ", text, " to the element: ", locator.toString());
        findElement(locator).sendKeys(text);
    }

    @Step("Clearing the text from element: {locator}")
    public void clear(By locator){

        wait.waitForElementToBeVisible(locator);
        scrollToElement(locator);
        WebElement element = findElement(locator);
        ReportManager.info("Clearing the text field: ", locator.toString(), "from text: ", element.getText());

        element.clear();
    }

    @Step("Clicking on the element: {locator}")
    public void click(By locator){

        wait.waitForElementToBeClickable(locator);
        scrollToElement(locator);
        ReportManager.info("Clicking on the element: ", locator.toString());
        findElement(locator).click();
    }

    @Step("Getting text from the element: {locator}")
    public String getText(By locator){
        wait.waitForElementToBeVisible(locator);
        scrollToElement(locator);
        ReportManager.info("Getting text from the element: ", locator.toString());
        return findElement(locator).getText();
    }

    private WebElement findElement(By locator){
        ReportManager.info("Finding element: ", locator.toString());
        return driver.findElement(locator);
    }

    @Step("Scrolling to element: {locator}")
    public void scrollToElement(By locator) {
        ReportManager.info("Scrolling to element: ", locator.toString());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

}
