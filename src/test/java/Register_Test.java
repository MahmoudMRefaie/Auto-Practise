import driver.DriverManager;
import listeners.TestNGListeners;
import org.framework.JSONFileManager;
import org.framework.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjectModels.HomePage;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(TestNGListeners.class)
public class Register_Test {
    private DriverManager driver;
    private HomePage home;
    private JSONFileManager testDataReader;

    @Test
    public void registerNewUser(){
        String registrationStatus = home.registerUser(testDataReader.getTestData("username") + String.valueOf(System.currentTimeMillis()),
                testDataReader.getTestData("password"));

        Assert.assertEquals(testDataReader.getTestData("signingUpSuccessfullyMessage"), registrationStatus);
    }

    @Test
    public void registerAlreadyExistUser(){
        String registrationStatus = home.registerUser(testDataReader.getTestData("existUser"),
                testDataReader.getTestData("password"));

        Assert.assertEquals(testDataReader.getTestData("failToSignupMessage"), registrationStatus);
    }

    @Test
    public void registerWithEmptyUsername(){
        String registrationStatus = home.registerUser("",
                testDataReader.getTestData("password"));

        Assert.assertEquals(testDataReader.getTestData("emptyUsernameOrPasswordMessage"), registrationStatus);
    }

    @Test
    public void registerWithEmptyPassword(){
        String registrationStatus = home.registerUser(testDataReader.getTestData("username") + String.valueOf(System.currentTimeMillis()),
                "");

        Assert.assertEquals(testDataReader.getTestData("emptyUsernameOrPasswordMessage"), registrationStatus);
    }

    @BeforeClass
    public void setUp() {

        driver = new DriverManager(PropertiesUtils.getPropertyValue("browserType"));
        home = new HomePage(driver);
        home.navigateToHome();
        testDataReader = new JSONFileManager("Register");
    }

    @AfterClass
    public void tearDown() {
        driver.browser().closeBrowser();
    }
}
