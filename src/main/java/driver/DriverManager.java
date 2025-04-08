package driver;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.*;

import java.net.MalformedURLException;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();

    public DriverManager(String browserName) {
        WebDriver driver = getDriver(browserName).startDriver();
        setDriver(driver);
    }

    public AbstractDriver getDriver(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeFactory();
            case "firefox" -> new FirefoxFactory();
            case "edge" -> new EdgeFactory();
            default -> throw new IllegalArgumentException("Invalid browser value");
        };
    }

    public WebDriver get() {
        if (driverThreadLocal.get() == null) {
            Assert.fail("Driver has not been initialized.");
        }
        return driverThreadLocal.get();
    }

    public static WebDriver getInstance() {
        return driverThreadLocal.get();
    }

    private static void setDriver(WebDriver driver) {
       driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        if(driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

    public BrowserActions browser() {
        return new BrowserActions(get());
    }

    public ElementActions element() {
        return new ElementActions(get());
    }

    public Validation validate() {
        return new Validation(get());
    }

    public AlertActions alert() {
        return new AlertActions(get());
    }
}
