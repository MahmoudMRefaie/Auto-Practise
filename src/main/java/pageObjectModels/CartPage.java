package pageObjectModels;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AlertActions;
import utils.ElementActions;

import java.time.Duration;

public class CartPage extends BasePage {
    private final DriverManager driver;
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

    public CartPage(DriverManager driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Selecting a random item")
    public void selectRandomItem(){

        driver.element().click(cardTitle);
    }

    @Step("Selecting item: {itemName}")
    public void selectItem(String itemName){

        driver.element().click(By.linkText(itemName));
//        By itemImage = RelativeLocator.with(By.tagName("a")).above(By.linkText(itemName));
//        driver.element().click(itemImage);

    }

    @Step("Adding item to cart")
    public String addItemToCart(){

        driver.element().click(btn_addToCart);

        WebDriverWait alertWait = new WebDriverWait(driver.get(), Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.get().switchTo().alert().getText();
        driver.get().switchTo().alert().accept();

        return alertMessage;
    }

    @Step("Deleting item")
    public void deleteItem() {

        driver.element().click(btn_deleteItem);
    }

    @Step("Deleting item: {itemName}")
    public void deleteItem(String itemName) {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(btn_deleteItem));

        By desiredItem = By.xpath("//text()[contains(.,\"" + itemName + "\")]");
        driver.get().findElement(desiredItem).findElement(By.xpath("parent::*")).findElement(btn_deleteItem).click();
    }

    @Step("Placing order")
    public void placeOrder() {

        driver.element().click(btn_placeOrder);
    }

    @Step("Adding buyer information: {fullName}, {country}, {city}, {creditCard}, {month}, {year}")
    public void addBuyerInfo(String fullName, String country, String city, String creditCard, String month, String year) {

        driver.element().sendKeys(nameInfo, fullName);
        driver.element().sendKeys(countryInfo, country);
        driver.element().sendKeys(cityInfo, city);
        driver.element().sendKeys(cardInfo, creditCard);
        driver.element().sendKeys(monthInfo, month);
        driver.element().sendKeys(yearInfo, year);
        driver.element().click(purchase);
    }

    @Step("Getting successfully purchased message")
    public String getSuccessfullyPurchasedMessage(){

        String message = driver.element().getText(purchasedMessage);
        driver.element().click(btn_okPurchased);
        return message;
    }

}
