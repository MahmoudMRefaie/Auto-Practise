package driver;

import org.framework.PropertiesUtils;
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
