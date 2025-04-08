import driver.DriverManager;
import listeners.TestNGListeners;
import org.framework.JSONFileManager;
import org.framework.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjectModels.CategoriesPage;
import pageObjectModels.CategoriesPage.Categories;
import utils.BrowserActions;

import java.util.List;

@Listeners(TestNGListeners.class)
public class Category_Test {
    private DriverManager driver;
    private CategoriesPage categoriesPage;
    private JSONFileManager testDataReader;

    @Test
    public void checkPhonesCategoryHasItems(){
        List<WebElement> items = categoriesPage.getCategoryItems(Categories.PHONES);

        Assert.assertNotEquals(0,items.size());
    }

    @Test
    public void checkLaptopsCategoryHasItems(){
        List<WebElement> items = categoriesPage.getCategoryItems(Categories.LAPTOPS);

        Assert.assertNotEquals(0,items.size());
    }

    @Test
    public void checkMonitorsCategoryHasItems(){
        List<WebElement> items = categoriesPage.getCategoryItems(Categories.MONITORS);

        Assert.assertNotEquals(0,items.size());
    }

    @BeforeClass
    public void setUp() {
        driver = new DriverManager(PropertiesUtils.getPropertyValue("browserType"));
        categoriesPage = new CategoriesPage(driver);
        categoriesPage.navigateToHome();
        testDataReader = new JSONFileManager("Category");
    }

    @AfterClass
    public void tearDown() {
        BrowserActions.closeBrowser(driver);
    }

}
