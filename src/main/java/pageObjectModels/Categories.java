package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Categories extends Main {
    private final WebDriver driver;
    private final By categoriesHeader = By.linkText("CATEGORIES");
    private final By phonesCatElement = By.linkText("Phones");
    private final By laptopsCatElement = By.linkText("Laptops");
    private final By monitorsCatElement = By.linkText("Monitors");
    private final By itemsContainer = By.id("tbodyid");

    public Categories(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public enum CategoriesElement {
        PHONES("Phones"), LAPTOPS("Laptops"), MONITORS("Monitors");
        private CategoriesElement(final String categoriesElement) {
            this.categoriesElement = categoriesElement;
        }

        private String categoriesElement;

        public String geCategoriesElement() {
            return categoriesElement;
        }
    }

    public List<WebElement> getCategoryItems(String categoryName) {
        List<WebElement> items = null;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(categoriesHeader));

        switch (categoryName){
        case "Phones":
            driver.findElement(phonesCatElement).click();
            WebElement container = driver.findElement(itemsContainer);
            WebDriverWait waitItem = new WebDriverWait(driver, Duration.ofSeconds(5));
            waitItem.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='tbodyid'] //div)[1]")));
            items = container.findElements(By.tagName("div"));
            break;
        case "Laptops":
            driver.findElement(laptopsCatElement).click();
            WebElement laptopsContainer = driver.findElement(itemsContainer);
            WebDriverWait laptopsContainerWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            laptopsContainerWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='tbodyid'] //div)[1]")));
            items = laptopsContainer.findElements(By.tagName("div"));
            break;
        case "Monitors":
            driver.findElement(monitorsCatElement).click();
            WebElement monitorsContainer = driver.findElement(itemsContainer);
            WebDriverWait monitorsContainerWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            monitorsContainerWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='tbodyid'] //div)[1]")));
            items = monitorsContainer.findElements(By.tagName("div"));
            break;
        }

        return items;
    }



}
