package pageObjectModels;

import com.google.gson.JsonObject;
import driver.DriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.framework.Logger;
import org.framework.PropertiesUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserActions;
import utils.ElementActions;

import static io.restassured.RestAssured.given;

public class BasePage {

    private final DriverManager driver;
    private final By home = By.linkText("Home");
    private final By login = By.linkText("Log in");
    private final By loginUsernameElement = By.id("loginusername");
    private final By loginPasswordElement = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[@onclick='logIn()']");
    private final By logout = By.linkText("Log out");

    public BasePage(DriverManager driver){
        this.driver = driver;
        //readProperties();
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

        driver.element().click(login);
        Logger.log("Login with username [" + username + "] and password [" + password + "]");
        driver.element().sendKeys(loginUsernameElement, username);
        driver.element().sendKeys(loginPasswordElement, password);
        driver.element().click(loginButton);
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
        RestAssured.baseURI = PropertiesUtils.getPropertyValue("API_URL");

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

        driver.element().click(logout);
        //driver.findElement(logout).click();
    }

    /**
     * This method is used to navigate to home page
     */
    @Step("Navigate to home page")
    public void navigateToHome() {
        driver.browser().navigateToURL(PropertiesUtils.getPropertyValue("BASE_WEB_URL"));
    }

    /**
     * This method is used to navigate to cart page
     */
    @Step("Navigate to cart page")
    public void navigateToCart() {
        driver.browser().navigateToURL(PropertiesUtils.getPropertyValue("BASE_WEB_URL") + "cart.html");
    }

}
