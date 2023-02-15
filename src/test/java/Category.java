import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModels.Categories;
import pageObjectModels.Categories.CategoriesElement;

import java.util.List;

public class Category {
    private WebDriver driver;
    private Categories categories;
    private JSONFileManager testDataReader;

    @Test
    public void checkPhonesCategoryHasItems(){
        List<WebElement> items = categories.getCategoryItems(CategoriesElement.PHONES.geCategoriesElement());

        Assert.assertNotEquals(0,items.size());
    }

    @Test
    public void checkLaptopsCategoryHasItems(){
        List<WebElement> items = categories.getCategoryItems(CategoriesElement.LAPTOPS.geCategoriesElement());

        Assert.assertNotEquals(0,items.size());
    }

    @Test
    public void checkMonitorsCategoryHasItems(){
        List<WebElement> items = categories.getCategoryItems(CategoriesElement.MONITORS.geCategoriesElement());

        Assert.assertNotEquals(0,items.size());
    }

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        categories = new Categories(driver);
        testDataReader = new JSONFileManager("src/main/resources/testDataFiles/Category.json");
    }

    @AfterClass
    public void tearDone() {
        driver.close();
    }

}
