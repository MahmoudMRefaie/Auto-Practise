package utils;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public static String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

}
