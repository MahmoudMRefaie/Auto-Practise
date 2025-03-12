package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ElementActions {

    private ElementActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void sendKeys(WebDriver driver, By locator, String text){

        clear(driver, locator);
        ReportManager.info("Sending text: ", text, " to the element: ", locator.toString());
        driver.findElement(locator).sendKeys(text);
    }

    public static void sendKeysAppend(WebDriver driver, By locator, String text){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        ReportManager.info("Sending text: ", text, " to the element: ", locator.toString());
        driver.findElement(locator).sendKeys(text);
    }

    public static void clear(WebDriver driver, By locator){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        WebElement element = driver.findElement(locator);
        ReportManager.info("Clearing the text field: ", locator.toString(), "from text: ", element.getText());

        element.clear();
    }

    public static void click(WebDriver driver, By locator){

        Waits.waitForElementToBeClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        ReportManager.info("Clicking on the element: ", locator.toString());
        driver.findElement(locator).click();
    }

    public static String getText(WebDriver driver, By locator){
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        ReportManager.info("Getting text from the element: ", locator.toString());
        return driver.findElement(locator).getText();
    }

}
