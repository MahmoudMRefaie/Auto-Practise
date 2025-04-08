package utils;

import driver.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private static final long TIMEOUT = 10;
    private Waits() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static WebElement waitForElementToBePresent(DriverManager driver, By locator) {
        ReportManager.info("Waiting for element to be present: ", locator.toString());
        return new WebDriverWait(driver.get(), Duration.ofSeconds(TIMEOUT))
                .until(driver1 -> driver1.findElement(locator));
    }

    public static Alert waitForAlertToBePresent(DriverManager driver) {
        ReportManager.info("Waiting for alert to be present in page: ", driver.get().getCurrentUrl());
        return new WebDriverWait(driver.get(), Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    public static WebElement waitForElementToBeVisible(DriverManager driver, By locator) {
        ReportManager.info("Waiting for element to be visible: ", locator.toString());
        return new WebDriverWait(driver.get(), Duration.ofSeconds(TIMEOUT))
                .until(driver1 -> {
                    WebElement element = waitForElementToBePresent(driver, locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    public static WebElement waitForElementToBeClickable(DriverManager driver, By locator) {
        ReportManager.info("Waiting for element to be clickable: ", locator.toString());
        return new WebDriverWait(driver.get(), Duration.ofSeconds(TIMEOUT))
                .until(driver1 -> {
                    WebElement element = waitForElementToBePresent(driver, locator);
                    return element.isEnabled() ? element : null;
                });

//        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
//                .until(driver1 -> waitForElementToBePresent(driver, locator).isEnabled() ? driver1 : null);

    }

}
