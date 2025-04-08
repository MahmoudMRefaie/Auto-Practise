package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {

    private Validation() {}

    @Step("Validating true: {condition}")
    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Step("Validating equals: {actual} and {expected}")
    public static void validateEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void validateNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    public static void validateNull(Object object, String message) {
        Assert.assertNull(object, message);
    }

    public static void validateNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public static void validatePageURL(DriverManager driver, String expectedURL) {
        Assert.assertEquals(BrowserActions.getCurrentURL(driver), expectedURL);
    }

    public static void validatePageTitle(DriverManager driver, String expectedTitle) {
        Assert.assertEquals(BrowserActions.getPageTitle(driver), expectedTitle);
    }
}
