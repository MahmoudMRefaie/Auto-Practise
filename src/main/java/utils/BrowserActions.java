package utils;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to URL: {url}")
    public void navigateToURL(String url) {
        ReportManager.info("Navigating to:", url);
        driver.get(url);
    }

    @Step("Getting current URL")
    public String getCurrentURL() {
        ReportManager.info("Getting current URL:", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public String getPageTitle() {
        ReportManager.info("Getting title:", driver.getTitle());
        return driver.getTitle();
    }

    @Step("Refreshing page")
    public void refreshPage() {
        ReportManager.info("Refreshing page:", driver.getCurrentUrl());
        driver.navigate().refresh();
    }

    @Step("Navigating back")
    public void navigateBack() {
        ReportManager.info("Navigating back from page:", driver.getCurrentUrl());
        driver.navigate().back();
    }

    @Step("Navigating forward")
    public void navigateForward() {
        ReportManager.info("Navigating forward from page:", driver.getCurrentUrl());
        driver.navigate().forward();
    }

    @Step("Closing browser")
    public void closeBrowser() {
        ReportManager.info("Closing browser");
        DriverManager.quitDriver();
    }

}
