import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModels.HomePage;


public class Register {
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


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        home = new HomePage(driver);
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Register.json");
    }

    @AfterClass
    public void afterClass() {
        //driver.close();
    }
}
