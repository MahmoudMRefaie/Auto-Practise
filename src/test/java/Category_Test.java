import driver.DriverManager;
import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModels.CategoriesPage;
import pageObjectModels.CategoriesPage.Categories;

import java.util.List;

public class Category_Test {
    private WebDriver driver;
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
        driver = DriverManager.createInstance("safari").getDriver();
        categoriesPage = new CategoriesPage(driver);
        categoriesPage.navigateToHome();
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Category.json");
    }

    @AfterClass
    public void tearDown() {
        //driver.close();
        driver.quit();
    }

}
