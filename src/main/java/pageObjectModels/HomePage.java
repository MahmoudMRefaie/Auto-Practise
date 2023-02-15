package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final By signup = By.linkText("Sign up");
    private final By usernameElement = By.id("sign-username");
    private final By passwordElement = By.id("sign-password");
    private final By signupButton = By.xpath("//button[@onclick='register()']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String registerUser(String username, String password){
        driver.findElement(signup).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));   //Explicit wait
        wait.until(ExpectedConditions.elementToBeClickable(usernameElement));

        System.out.println("Register with username [" + username + "] and password [" + password + "]");
        driver.findElement(usernameElement).clear();
        driver.findElement(usernameElement).sendKeys(username);
        driver.findElement(passwordElement).clear();
        driver.findElement(passwordElement).sendKeys(password);
        driver.findElement(signupButton).click();

        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();

        return alertMessage;
    }

}
