package pageObjectModels;

import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.framework.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserActions;
import utils.ElementActions;

import static io.restassured.RestAssured.given;

public class BasePage {

    private final WebDriver driver;
    private final By home = By.linkText("Home");
    private final By login = By.linkText("Log in");
    private final By loginUsernameElement = By.id("loginusername");
    private final By loginPasswordElement = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[@onclick='logIn()']");
    private final By logout = By.linkText("Log out");

    public BasePage(WebDriver driver){
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
    @Step("Login with username: {username} and password: {password}")
    public void login(String username, String password) {

        ElementActions.click(driver, login);
        //driver.findElement(login).click();

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        wait.until(ExpectedConditions.elementToBeClickable(loginUsernameElement));

        Logger.log("Login with username [" + username + "] and password [" + password + "]");
//        ElementActions.clear(driver, loginUsernameElement);
//        driver.findElement(loginUsernameElement).clear();
        ElementActions.sendKeys(driver, loginUsernameElement, username);
        //driver.findElement(loginUsernameElement).sendKeys(username);
//        ElementActions.clear(driver, loginPasswordElement);
//        driver.findElement(loginPasswordElement).clear();
        ElementActions.sendKeys(driver, loginPasswordElement, password);
        //driver.findElement(loginPasswordElement).sendKeys(password);
        ElementActions.click(driver, loginButton);
        //driver.findElement(loginButton).click();
    }

    /**
     * This method is used to login using api to facilitate and accelerate the execution the as we test a login in a separated suit
     * @param username valid username
     * @param password valid password
     * @return  access token
     */
    @Step("Login using API with username: {username} and password: {password}")
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
    @Step("Logout")
    public void logout() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        wait.until(ExpectedConditions.elementToBeClickable(logout));

        ElementActions.click(driver, logout);
        //driver.findElement(logout).click();
    }

    /**
     * This method is used to navigate to home page
     */
    @Step("Navigate to home page")
    public void navigateToHome() {
        BrowserActions.navigateToURL(driver, "https://www.demoblaze.com/");
    }

    /**
     * This method is used to navigate to cart page
     */
    @Step("Navigate to cart page")
    public void navigateToCart() {
        BrowserActions.navigateToURL(driver,"https://www.demoblaze.com/cart.html");
    }

}
