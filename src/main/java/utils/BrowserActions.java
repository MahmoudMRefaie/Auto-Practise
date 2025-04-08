package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    @Step("Navigate to URL: {url}")
    public static void navigateToURL(DriverManager driver, String url) {
        ReportManager.info("Navigating to:", url);
        driver.get().get(url);
    }

    @Step("Getting current URL")
    public static String getCurrentURL(DriverManager driver) {
        ReportManager.info("Getting current URL:", driver.get().getCurrentUrl());
        return driver.get().getCurrentUrl();
    }

    @Step("Getting page title")
    public static String getPageTitle(DriverManager driver) {
        ReportManager.info("Getting title:", driver.get().getTitle());
        return driver.get().getTitle();
    }

    @Step("Refreshing page")
    public static void refreshPage(DriverManager driver) {
        ReportManager.info("Refreshing page:", driver.get().getCurrentUrl());
        driver.get().navigate().refresh();
    }

    @Step("Navigating back")
    public static void navigateBack(DriverManager driver) {
        ReportManager.info("Navigating back from page:", driver.get().getCurrentUrl());
        driver.get().navigate().back();
    }

    @Step("Navigating forward")
    public static void navigateForward(DriverManager driver) {
        ReportManager.info("Navigating forward from page:", driver.get().getCurrentUrl());
        driver.get().navigate().forward();
    }

    @Step("Closing browser")
    public static void closeBrowser(DriverManager driver) {
        ReportManager.info("Closing browser");
        DriverManager.quitDriver();
    }

}
