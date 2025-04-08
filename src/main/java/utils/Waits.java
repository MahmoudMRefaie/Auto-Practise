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

    private final WebDriver driver;
    private static final long TIMEOUT = 10;

    public Waits(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToBePresent(By locator) {
        ReportManager.info("Waiting for element to be present: ", locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver1 -> driver1.findElement(locator));
    }

    public Alert waitForAlertToBePresent() {
        ReportManager.info("Waiting for alert to be present in page: ", driver.getCurrentUrl());
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    public WebElement waitForElementToBeVisible(By locator) {
        ReportManager.info("Waiting for element to be visible: ", locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver1 -> {
                    WebElement element = waitForElementToBePresent(locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    public WebElement waitForElementToBeClickable(By locator) {
        ReportManager.info("Waiting for element to be clickable: ", locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver1 -> {
                    WebElement element = waitForElementToBePresent(locator);
                    return element.isEnabled() ? element : null;
                });

//        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
//                .until(driver1 -> waitForElementToBePresent(driver, locator).isEnabled() ? driver1 : null);

    }

}
