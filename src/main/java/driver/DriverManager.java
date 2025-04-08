package driver;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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

    public static WebDriver get() {
        if (driverThreadLocal.get() == null) {
            Assert.fail("Driver has not been initialized.");
        }
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
}
