package pageObjectModels;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.framework.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AlertActions;
import utils.ElementActions;

import java.time.Duration;

public class HomePage extends BasePage {

    private final DriverManager driver;
    private final By signup = By.linkText("Sign up");
    private final By registerUsernameElement = By.id("sign-username");
    private final By registerPasswordElement = By.id("sign-password");
    private final By signupButton = By.xpath("//button[@onclick='register()']");

    private final By nameOfUser = By.id("nameofuser");

    public HomePage(DriverManager driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * This method is used to register a new user
     * @param username desired registered username
     * @param password desired registered password
     * @return registration message that displayed in the alert
     */
    @Step("Register with username: {username} and password: {password}")
    public String registerUser(String username, String password) {

        driver.element().click(signup);

        Logger.log("Register with username [" + username + "] and password [" + password + "]");
        driver.element().sendKeys(registerUsernameElement, username);
        driver.element().sendKeys(registerPasswordElement, password);
        driver.element().click(signupButton);

        String alertMessage = driver.alert().getAlertText();
        driver.alert().acceptAlert();

        Actions action = new Actions(driver.get());
        action.moveByOffset(0, 0).click().build().perform();

        return alertMessage;
    }

    /**
     * This method is used to retrieve welcoming message after logging in
     * @return welcoming message for the user
     */
    @Step("Get welcoming message")
    public String getWelcomingMessage() {
        return driver.element().getText(nameOfUser);
    }

    /**
     * This method is user to retrieve login error message
     * @return login error message
     */
    @Step("Get login error message")
    public String getLoginError(){
        WebDriverWait alertWait = new WebDriverWait(driver.get(), Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.get().switchTo().alert().getText();
        driver.get().switchTo().alert().accept();

        Actions action = new Actions(driver.get());
        action.moveByOffset(0, 0).click().build().perform();   //to return to the original page

        return alertMessage;
    }

}
