package utils;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void navigateToURL(WebDriver driver, String url) {
        ReportManager.info("Navigating to: ", url);
        driver.get(url);
    }

    public static String getCurrentURL(WebDriver driver) {
        ReportManager.info("Getting current URL: ", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public static String getPageTitle(WebDriver driver) {
        ReportManager.info("Getting title: ", driver.getTitle());
        return driver.getTitle();
    }

    public static void refreshPage(WebDriver driver) {
        ReportManager.info("Refreshing page: ", driver.getCurrentUrl());
        driver.navigate().refresh();
    }

}
