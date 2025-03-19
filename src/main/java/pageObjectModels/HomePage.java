package pageObjectModels;

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

    private final WebDriver driver;
    private final By signup = By.linkText("Sign up");
    private final By registerUsernameElement = By.id("sign-username");
    private final By registerPasswordElement = By.id("sign-password");
    private final By signupButton = By.xpath("//button[@onclick='register()']");

    private final By nameOfUser = By.id("nameofuser");

    public HomePage(WebDriver driver) {
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
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));   //Explicit wait
//        wait.until(ExpectedConditions.elementToBeClickable(signup));

        ElementActions.click(driver, signup);
        //driver.findElement(signup).click();

//        wait.until(ExpectedConditions.elementToBeClickable(registerUsernameElement));

        Logger.log("Register with username [" + username + "] and password [" + password + "]");
//        driver.findElement(registerUsernameElement).clear();
        ElementActions.sendKeys(driver, registerUsernameElement, username);
        //driver.findElement(registerUsernameElement).sendKeys(username);
//        driver.findElement(registerPasswordElement).clear();
        ElementActions.sendKeys(driver, registerPasswordElement, password);
        //driver.findElement(registerPasswordElement).sendKeys(password);
        ElementActions.click(driver, signupButton);
        //driver.findElement(signupButton).click();

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));   //Explicit wait
//        wait.until(ExpectedConditions.alertIsPresent());
//        String alertMessage = driver.switchTo().alert().getText();
//        driver.switchTo().alert().accept();

        String alertMessage = AlertActions.getAlertText(driver);
        AlertActions.acceptAlert(driver);

        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();

        return alertMessage;
    }

    /**
     * This method is used to retrieve welcoming message after logging in
     * @return welcoming message for the user
     */
    @Step("Get welcoming message")
    public String getWelcomingMessage() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        wait.until(ExpectedConditions.elementToBeClickable(nameOfUser));

        return ElementActions.getText(driver, nameOfUser);
        //return driver.findElement(nameOfUser).getText();
    }

    /**
     * This method is user to retrieve login error message
     * @return login error message
     */
    @Step("Get login error message")
    public String getLoginError(){
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();   //to return to the original page

        return alertMessage;
    }

}
