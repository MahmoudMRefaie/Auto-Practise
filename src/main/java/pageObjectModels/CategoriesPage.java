package pageObjectModels;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementActions;

import java.time.Duration;
import java.util.List;

public class CategoriesPage extends BasePage {
    private final DriverManager driver;
    private final By categoriesHeader = By.linkText("CATEGORIES");
    private final By phonesCatElement = By.linkText("Phones");
    private final By laptopsCatElement = By.linkText("Laptops");
    private final By monitorsCatElement = By.linkText("Monitors");
    private final By itemsContainer = By.id("tbodyid");

    public CategoriesPage(DriverManager driver) {
        super(driver);
        this.driver = driver;
    }

    public enum Categories {
        PHONES("Phones"), LAPTOPS("Laptops"), MONITORS("Monitors");
        private Categories(final String categoryItem) {
            this.categoryItem = categoryItem;
        }

        private String categoryItem;

        public String geCategoryName() {
            return categoryItem;
        }
    }

    @Step("Getting items for category: {category}")
    public List<WebElement> getCategoryItems(Categories category) {
        List<WebElement> items = null;
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(categoriesHeader));

        switch (category.geCategoryName()){
        case "Phones":
            ElementActions.click(driver, phonesCatElement);
            //driver.findElement(phonesCatElement).click();
            WebElement container = driver.get().findElement(itemsContainer);
            WebDriverWait waitItem = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
            waitItem.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='tbodyid'] //div)[1]")));
            items = container.findElements(By.tagName("div"));
            break;
        case "Laptops":
            ElementActions.click(driver, laptopsCatElement);
            //driver.findElement(laptopsCatElement).click();
            WebElement laptopsContainer = driver.get().findElement(itemsContainer);
            WebDriverWait laptopsContainerWait = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
            laptopsContainerWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='tbodyid'] //div)[1]")));
            items = laptopsContainer.findElements(By.tagName("div"));
            break;
        case "Monitors":
            ElementActions.click(driver, monitorsCatElement);
            //driver.findElement(monitorsCatElement).click();
            WebElement monitorsContainer = driver.get().findElement(itemsContainer);
            WebDriverWait monitorsContainerWait = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
            monitorsContainerWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='tbodyid'] //div)[1]")));
            items = monitorsContainer.findElements(By.tagName("div"));
            break;
        }

        return items;
    }



}
