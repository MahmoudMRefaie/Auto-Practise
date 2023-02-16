package pageObjectModels;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Main {

    private final WebDriver driver;
    private final By home = By.linkText("Home");
    private final By login = By.linkText("Log in");
    private final By loginUsernameElement = By.id("loginusername");
    private final By loginPasswordElement = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[@onclick='logIn()']");
    private final By logout = By.linkText("Log out");

    public Main(WebDriver driver){
        this.driver = driver;
        readProperties();
    }

    private void readProperties() {
//        FileReader reader = null;
//        Properties prop = null;
//        try {
//            reader = new FileReader("src/main/resources/properties/demoblaze.properties");
//            prop = new Properties();
//            prop.load(reader);
//        } catch (FileNotFoundException e) {
//            System.out.println("Can't read the file");
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            System.out.println("Can't read this property");
//            throw new RuntimeException(e);
//        }
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
     * This method is used to login using api to facilitate and accelerate the execution the as we test a login in a separated suit
     * @param username valid username
     * @param password valid password
     * @return  access token
     */
    public String loginAPI(String username, String password) {
        if(password.equals("password"))     //password is encoded
            password = "cGFzc3dvcmQ=";

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", username);
        requestBody.addProperty("password",password);

        System.out.println(requestBody);
        RestAssured.baseURI = "https://api.demoblaze.com";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();

        System.out.println("User [" + username + "] is logged in");
        return response.body().asString().replace("\"Auth_token: ","").replace("\"","");
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
     * This method is used to navigate to home page
     */
    public void navigateToHome() {
        driver.get("https://www.demoblaze.com/");
    }

    /**
     * This method is used to navigate to cart page
     */
    public void navigateToCart() {
        driver.get("https://www.demoblaze.com/cart.html");
    }

}
