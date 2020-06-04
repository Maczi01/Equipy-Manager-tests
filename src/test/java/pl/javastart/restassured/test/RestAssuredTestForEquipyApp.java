package pl.javastart.restassured.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTestForEquipyApp {

    private final String JSON = "application/json";
    private final int OK = 200;
    private final int NOT_FOUND = 404;


    @Test
    public void givenExistingUserIdWhenGetUserThenReturnStatusOk() {
        given().when().get("http://localhost:8080/api/users/1").then().statusCode(OK);
    }

    @Test
    public void givenNonExistingUserIdWhenGetUserThenNotFoundStatus() {
        given().when().get("http://localhost:8080/api/users/999").then().statusCode(NOT_FOUND);
    }

    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {
        String user = "{\n" +
                "    \"firstName\": \"Jan\",\n" +
                "    \"lastName\": \"Jezioranski\",\n" +
                "    \"pesel\": \"789777287\"\n" +
                "}";

        given().log().all().body(user)
                .contentType(JSON)
                .when()
                .post("http://localhost:8080/api/users/").then().log().body().statusCode(OK);
    }

    @Test
    public void givenNonExistingUserIdWhenGetUserThenUserNotFoundTest() {
        given().when().get("http://localhost:8080/api/users/0").then().statusCode(NOT_FOUND);
    }


    @Test
    public void givenExistingUserIdWhenGetUserThenReturnUserTestWithPathParam() {
        given().log().method().log().uri()
                .pathParam("petId", 2)
                .when().get("http://localhost:8080/api/users/{petId}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingUserWhenUpdateUserNameThenUserIsChangedTest() {
        String user = "{\n" +
                "    \"firstName\": \"Donald\",\n" +
                "    \"lastName\": \"Bradley\",\n" +
                "    \"pesel\": \"9902322\"\n" +
                "}";


        given().log().all().body(user).contentType(JSON)
                .when().post("http://localhost:8080/api/users")
                .then().log().all().statusCode(OK);


        user = "{\n" +
                "    \"firstName\": \"Mike\",\n" +
                "    \"lastName\": \"Bradley\",\n" +
                "    \"pesel\": \"9902322\"\n" +
                "}";

        given().log().all().body(user).contentType(JSON)
                .when().put("http://localhost:8080/api/users")
                .then().log().all().statusCode(OK);
    }
    @Test
    public void givenExistingUserIdWhenDeletingUserThenIsDeletedTest() {

        given().log().all().contentType(JSON)
                .when().delete("http://localhost:8080/api/users/2")
                .then().log().all().statusCode(OK);
    }
}
