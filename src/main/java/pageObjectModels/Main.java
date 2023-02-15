package pageObjectModels;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Main {

    public Main(){
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
     * This method is used to login using api to facilitate and accelerate the execution the as we test a login in a separated suit
     * @param username valid username
     * @param password valid password
     * @return  access token
     */
    public String loginAuth(String username, String password) {
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

        return response.body().asString().replace("\"Auth_token: ","").replace("\"","");
    }
}
