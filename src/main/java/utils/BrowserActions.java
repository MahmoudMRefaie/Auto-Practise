package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    @Step("Navigate to URL: {url}")
    public static void navigateToURL(WebDriver driver, String url) {
        ReportManager.info("Navigating to: ", url);
        driver.get(url);
    }

    @Step("Getting current URL")
    public static String getCurrentURL(WebDriver driver) {
        ReportManager.info("Getting current URL: ", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public static String getPageTitle(WebDriver driver) {
        ReportManager.info("Getting title: ", driver.getTitle());
        return driver.getTitle();
    }

    @Step("Refreshing page")
    public static void refreshPage(WebDriver driver) {
        ReportManager.info("Refreshing page: ", driver.getCurrentUrl());
        driver.navigate().refresh();
    }

    @Step("Navigating back")
    public static void navigateBack(WebDriver driver) {
        ReportManager.info("Refreshing page: ", driver.getCurrentUrl());
        driver.navigate().back();
    }

}
