package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scrolling {

    private Scrolling() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    @Step("Scrolling to element: {locator}")
    public static void scrollToElement(DriverManager driver, By locator) {
        ReportManager.info("Scrolling to element: ", locator.toString());
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", driver.get().findElement(locator));
    }
}
