import driver.DriverManager;
import org.framework.JSONFileManager;
import org.framework.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjectModels.CartPage;
import utils.AllureUtils;
import utils.FilesUtils;
import utils.ScreenshotsUtils;

import java.io.File;

public class Cart_Test {
    private WebDriver driver;
    private CartPage cartPage;
    private JSONFileManager testDataReader;

    @Test
    public void addRandomItemToCart() {
        cartPage.selectRandomItem();
        String itemStatus = cartPage.addItemToCart();

        Assert.assertEquals(testDataReader.getTestData("productAddedMessage"), itemStatus);
    }

    @Test
    public void addSpecificItemToCart() {
        cartPage.selectItem(testDataReader.getTestData("itemName"));
        String itemStatus = cartPage.addItemToCart();

        Assert.assertEquals(testDataReader.getTestData("productAddedMessage"), itemStatus);
    }

    @Test
    public void removeItemFromCart() {
        cartPage.navigateToCart();
        cartPage.deleteItem();
    }

    @Test(priority = 1)
    public void completeCheckout() {
        cartPage.navigateToCart();
        cartPage.placeOrder();
        cartPage.addBuyerInfo(testDataReader.getTestData("fullName"),
                testDataReader.getTestData("country"),
                testDataReader.getTestData("city"),
                testDataReader.getTestData("credit_card"),
                testDataReader.getTestData("month"),
                testDataReader.getTestData("year")
        );

        Assert.assertEquals(testDataReader.getTestData("successfullyPurchasedMessage"),
                cartPage.getSuccessfullyPurchasedMessage());

        ScreenshotsUtils.takeScreenshot("success-checkout");
    }

    @AfterMethod
    public void returnToHome(){
        cartPage.navigateToHome();
    }

    @BeforeClass
    public void setUp() {
        driver = DriverManager.createInstance(PropertiesUtils.getPropertyValue("browserType")).getDriver();
        cartPage = new CartPage(driver);
        cartPage.navigateToHome();
        testDataReader = new JSONFileManager("Cart");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        AllureUtils.attachLogsToAllureReport();
    }

    @BeforeSuite
    public void setupSuite() {
        FilesUtils.deleteFile(new File("test-outputs/allure-results"));      //Should be handled at onStart Listeners for TestNG Listeners
        PropertiesUtils.loadProperties();                                             //Should be handled at onStart Listeners for TestNG Listeners
    }

}
