import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModels.HomePage;

public class Register {
    private WebDriver driver;
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
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver");
//        ChromeOptions option = new ChromeOptions();
//        option.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.demoblaze.com/");
        home = new HomePage(driver);
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Register.json");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
