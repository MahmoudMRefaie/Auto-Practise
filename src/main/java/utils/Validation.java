package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {

    private final BrowserActions browserActions;

    public Validation(WebDriver driver) {
        browserActions = new BrowserActions(driver);
    }

    @Step("Validating true: {condition}")
    public void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Step("Validating equals: {actual} and {expected}")
    public void validateEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public void validateNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    public void validateNull(Object object, String message) {
        Assert.assertNull(object, message);
    }

    public void validateNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public void validatePageURL(String expectedURL) {
        Assert.assertEquals(browserActions.getCurrentURL(), expectedURL);
    }

    public void validatePageTitle(String expectedTitle) {
        Assert.assertEquals(browserActions.getPageTitle(), expectedTitle);
    }
}
