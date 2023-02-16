import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModels.CartPage;
import pageObjectModels.CategoriesPage;
import pageObjectModels.CategoriesPage.CategoriesElement;

import java.time.Duration;
import java.util.List;

public class Cart {
    private WebDriver driver;
    private CartPage cartPage;
    private JSONFileManager testDataReader;

    @Test
    public void addRandomItemToCart(){
        cartPage.selectRandomItem();
        String itemStatus = cartPage.addItemToCart();

        Assert.assertEquals(testDataReader.getTestData("productAddedMessage"), itemStatus);
    }

    @Test
    public void addSpecificItemToCart(){
        cartPage.selectItem(testDataReader.getTestData("itemName"));
        String itemStatus = cartPage.addItemToCart();

        Assert.assertEquals(testDataReader.getTestData("productAddedMessage"), itemStatus);
    }

    @Test
    public void removeItemFromCart(){
        cartPage.navigateToCart();
        cartPage.deleteItem();
    }

    @Test(priority = 1)
    public void completeCheckout(){
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
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        cartPage = new CartPage(driver);
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Cart.json");
    }

    @AfterClass
    public void tearDone() {
        driver.close();
    }

}
