package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ElementActions {

    private ElementActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    @Step("Sending text: {text} to the element: {locator}")
    public static void sendKeys(WebDriver driver, By locator, String text){

        clear(driver, locator);
        ReportManager.info("Sending text: ", text, " to the element: ", locator.toString());
        findElement(driver, locator).sendKeys(text);
    }

    @Step("Sending append text: {text} to the element: {locator}")
    public static void sendKeysAppend(WebDriver driver, By locator, String text){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        ReportManager.info("Sending text: ", text, " to the element: ", locator.toString());
        findElement(driver, locator).sendKeys(text);
    }

    @Step("Clearing the text from element: {locator}")
    public static void clear(WebDriver driver, By locator){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        WebElement element = findElement(driver, locator);
        ReportManager.info("Clearing the text field: ", locator.toString(), "from text: ", element.getText());

        element.clear();
    }

    @Step("Clicking on the element: {locator}")
    public static void click(WebDriver driver, By locator){

        Waits.waitForElementToBeClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        ReportManager.info("Clicking on the element: ", locator.toString());
        findElement(driver, locator).click();
    }

    @Step("Getting text from the element: {locator}")
    public static String getText(WebDriver driver, By locator){
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        ReportManager.info("Getting text from the element: ", locator.toString());
        return findElement(driver, locator).getText();
    }

    private static WebElement findElement(WebDriver driver, By locator){
        ReportManager.info("Finding element: ", locator.toString());
        return driver.findElement(locator);
    }

}
