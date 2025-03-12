package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scrolling {

    private Scrolling() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        ReportManager.info("Scrolling to element: ", locator.toString());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }
}
