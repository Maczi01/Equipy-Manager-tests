package pl.javastart.restassured.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTestForEquipyApp {

    @Test
    public void givenExistingUserIdWhenGetUserThenReturnStatusOk() {
                given().when().get("http://localhost:8080/api/users/1").then().statusCode(200);
    }

    @Test
    public void givenNonExistingUserIdWhenGetUserThenNotFoundStatus() {
        given().when().get("http://localhost:8080/api/users/999").then().statusCode(404);
    }

}
