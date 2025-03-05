import driver.DriverManager;
import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModels.CartPage;

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
    }

    @AfterMethod
    public void returnToHome(){
        cartPage.navigateToHome();
    }

    @BeforeClass
    public void setUp() {
        driver = DriverManager.createInstance("firefox").getDriver();
        cartPage = new CartPage(driver);
        cartPage.navigateToHome();
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Cart.json");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
