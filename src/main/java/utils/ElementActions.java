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
        driver.findElement(locator).sendKeys(text);
    }

    public static void sendKeysAppend(WebDriver driver, By locator, String text){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).sendKeys(text);
    }

    public static void clear(WebDriver driver, By locator){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).clear();
    }

    public static void click(WebDriver driver, By locator){

        Waits.waitForElementToBeClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).click();
    }

    public static String getText(WebDriver driver, By locator){
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return driver.findElement(locator).getText();
    }

}
