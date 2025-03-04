package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    private final WebDriver driver;
    private final By cardTitle = By.xpath("//h4 [@class='card-title']");
    private final By btn_placeOrder = By.xpath("//button [@class='btn btn-success']");
    private final By btn_addToCart = By.linkText("Add to cart");
    private final By btn_deleteItem = By.linkText("Delete");
    private final By nameInfo = By.id("name");
    private final By countryInfo = By.id("country");
    private final By cityInfo = By.id("city");
    private final By cardInfo = By.id("card");
    private final By monthInfo = By.id("month");
    private final By yearInfo = By.id("year");
    private final By purchase = By.xpath("//button [@onclick='purchaseOrder()']");
    private final By purchasedMessage = By.xpath("//div [@data-has-confirm-button='true'] //h2");
    private final By btn_okPurchased = By.xpath("//button [@class='confirm btn btn-lg btn-primary']");

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void selectRandomItem(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(cardTitle));

        driver.findElement(cardTitle).click();
    }

    public void selectItem(String itemName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(cardTitle));

        driver.findElement(By.linkText(itemName)).click();
    }

    public String addItemToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(btn_addToCart));

        driver.findElement(btn_addToCart).click();

        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        return alertMessage;
    }

    public void deleteItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(btn_deleteItem));

        driver.findElement(btn_deleteItem).click();
    }

    public void deleteItem(String itemName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(btn_deleteItem));

        By desiredItem = By.xpath("//text()[contains(.,\"" + itemName + "\")]");
        driver.findElement(desiredItem).findElement(By.xpath("parent::*")).findElement(btn_deleteItem).click();
    }

    public void placeOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(btn_placeOrder));

        driver.findElement(btn_placeOrder).click();
    }

    public void addBuyerInfo(String fullName, String country, String city, String creditCard, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(purchase));

        driver.findElement(nameInfo).clear();
        driver.findElement(nameInfo).sendKeys(fullName);
        driver.findElement(countryInfo).clear();
        driver.findElement(countryInfo).sendKeys(country);
        driver.findElement(cityInfo).clear();
        driver.findElement(cityInfo).sendKeys(city);
        driver.findElement(cardInfo).clear();
        driver.findElement(cardInfo).sendKeys(creditCard);
        driver.findElement(monthInfo).clear();
        driver.findElement(monthInfo).sendKeys(month);
        driver.findElement(yearInfo).clear();
        driver.findElement(yearInfo).sendKeys(year);
        driver.findElement(purchase).click();
    }

    public String getSuccessfullyPurchasedMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(btn_okPurchased));
        String message = driver.findElement(purchasedMessage).getText();
        driver.findElement(btn_okPurchased).click();
        return message;
    }

}
