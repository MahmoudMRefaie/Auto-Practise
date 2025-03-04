package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {

    private Validation() {}

    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

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

    public static void validatePageURL(WebDriver driver, String expectedURL) {
        Assert.assertEquals(BrowserActions.getCurrentURL(driver), expectedURL);
    }

    public static void validatePageTitle(WebDriver driver, String expectedTitle) {
        Assert.assertEquals(BrowserActions.getPageTitle(driver), expectedTitle);
    }
}
