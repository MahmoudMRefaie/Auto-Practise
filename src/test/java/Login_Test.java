import driver.DriverManager;
import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjectModels.HomePage;

public class Login_Test {
    private WebDriver driver;
    private HomePage home;
    private JSONFileManager testDataReader;

    @Test(priority = 1)
    public void loginWithCorrectUser(){
        String loginUsername = testDataReader.getTestData("username");

        home.login(loginUsername, testDataReader.getTestData("password"));

        Assert.assertEquals("Welcome " + loginUsername, home.getWelcomingMessage());
    }

    @Test
    public void loginWithNotExistUser(){
        home.login(testDataReader.getTestData("notExistUser"),
                testDataReader.getTestData("password"));

        Assert.assertEquals(testDataReader.getTestData("notExistUserMessage"), home.getLoginError());
    }

    @Test
    public void loginWithWrongPassword(){
        home.login(testDataReader.getTestData("username"),
                testDataReader.getTestData("wrongPassword"));

        Assert.assertEquals(testDataReader.getTestData("wrongPasswordMessage"), home.getLoginError());
    }

    @Test
    public void loginWithEmptyUsername(){
        home.login("", testDataReader.getTestData("wrongPassword"));

        Assert.assertEquals(testDataReader.getTestData("emptyUsernameOrPassword"), home.getLoginError());
    }

    @Test
    public void loginWithEmptyPassword(){
        home.login(testDataReader.getTestData("username"), "");

        Assert.assertEquals(testDataReader.getTestData("emptyUsernameOrPassword"), home.getLoginError());
    }

    @Test
    public void loginWithEmptyUsernameAndPassword(){
        home.login("", "");

        Assert.assertEquals(testDataReader.getTestData("emptyUsernameOrPassword"), home.getLoginError());
    }

    @Test
    public void loginCaseSensitiveUsername(){
        home.login(testDataReader.getTestData("caseSensitiveUsername"),
                testDataReader.getTestData("password"));

        Assert.assertEquals(testDataReader.getTestData("notExistUserMessage"), home.getLoginError());
    }

    @BeforeClass
    public void setUp() {
        driver = DriverManager.createInstance("chrome").getDriver();
        home = new HomePage(driver);
        home.navigateToHome();
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Login.json");
    }

    @AfterClass
    public void tearDown() {
        home.logout();
        driver.close();
    }
}
