package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends Main {

    private final WebDriver driver;
    private final By signup = By.linkText("Sign up");
    private final By registerUsernameElement = By.id("sign-username");
    private final By registerPasswordElement = By.id("sign-password");
    private final By signupButton = By.xpath("//button[@onclick='register()']");
    private final By login = By.linkText("Log in");
    private final By loginUsernameElement = By.id("loginusername");
    private final By loginPasswordElement = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[@onclick='logIn()']");
    private final By nameOfUser = By.id("nameofuser");
    private final By logout = By.linkText("Log out");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method is used to register a new user
     * @param username desired registered username
     * @param password desired registered password
     * @return registration message that displayed in the alert
     */
    public String registerUser(String username, String password) {
        driver.findElement(signup).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));   //Explicit wait
        wait.until(ExpectedConditions.elementToBeClickable(registerUsernameElement));

        System.out.println("Register with username [" + username + "] and password [" + password + "]");
        driver.findElement(registerUsernameElement).clear();
        driver.findElement(registerUsernameElement).sendKeys(username);
        driver.findElement(registerPasswordElement).clear();
        driver.findElement(registerPasswordElement).sendKeys(password);
        driver.findElement(signupButton).click();

        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();

        return alertMessage;
    }

    /**
     * This method is used to login to the system
     * @param username username
     * @param password password
     */
    public void login(String username, String password) {
        driver.findElement(login).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(loginUsernameElement));

        System.out.println("Login with username [" + username + "] and password [" + password + "]");
        driver.findElement(loginUsernameElement).clear();
        driver.findElement(loginUsernameElement).sendKeys(username);
        driver.findElement(loginPasswordElement).clear();
        driver.findElement(loginPasswordElement).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    /**
     * This method is used to logout from the system
     */
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(logout));

        driver.findElement(logout).click();
    }

    /**
     * This method is used to retrieve welcoming message after logging in
     * @return welcoming message for the user
     */
    public String getWelcomingMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(nameOfUser));

        return driver.findElement(nameOfUser).getText();
    }

    /**
     * This method is user to retrieve login error message
     * @return login error message
     */
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
