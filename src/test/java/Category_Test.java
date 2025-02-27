import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModels.CategoriesPage;
import pageObjectModels.CategoriesPage.CategoriesElement;

import java.util.List;

public class Category_Test {
    private WebDriver driver;
    private CategoriesPage categoriesPage;
    private JSONFileManager testDataReader;

    @Test
    public void checkPhonesCategoryHasItems(){
        List<WebElement> items = categoriesPage.getCategoryItems(CategoriesElement.PHONES.geCategoriesElement());

        Assert.assertNotEquals(0,items.size());
    }

    @Test
    public void checkLaptopsCategoryHasItems(){
        List<WebElement> items = categoriesPage.getCategoryItems(CategoriesElement.LAPTOPS.geCategoriesElement());

        Assert.assertNotEquals(0,items.size());
    }

    @Test
    public void checkMonitorsCategoryHasItems(){
        List<WebElement> items = categoriesPage.getCategoryItems(CategoriesElement.MONITORS.geCategoriesElement());

        Assert.assertNotEquals(0,items.size());
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        categoriesPage = new CategoriesPage(driver);
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Category.json");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
