package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class BrowserFactory {
    public static WebDriver geBrowser(String browser) throws MalformedURLException {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-infobars");
                Map<String, Object> prefs = Map.of("profile.default_content_setting_values.notifications", 2,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //firefoxOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-extensions");
                firefoxOptions.addArguments("--disable-infobars");
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                firefoxOptions.setAcceptInsecureCerts(true);
                //firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                SafariDriver safariDriver = new SafariDriver(safariOptions);
                safariDriver.manage().window().maximize();
                return safariDriver;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized",
                        "--disable-extensions",
                        "--disable-notifications",
                        "--remote-allow-origins=*",
                        "--disable-infobars");
//                edgeOptions.addArguments("--disable-extensions");
//                edgeOptions.addArguments("--disable-notifications");
//                edgeOptions.addArguments("--remote-allow-origins=*");
//                edgeOptions.addArguments("--disable-infobars");
                Map<String, Object> edgePrefs = Map.of("profile.default_content_setting_values.notifications", 2,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile_enabled", false);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);

                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //firefoxOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);
            case "remote":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//              String gridURL = "https://faab-109-83-121-90.ngrok-free.app";       // using ngrok at VM
                String gridURL = "http://localhost:4444";                           // using ssh tunnelling on VM
//              driver = new RemoteWebDriver(new URL(gridURL), options);
                //firefoxOptions.addArguments("--headless");
                return new RemoteWebDriver(new URL(gridURL), options);
            default:
                throw new IllegalArgumentException("Invalid browser value");
        }
    }
}
