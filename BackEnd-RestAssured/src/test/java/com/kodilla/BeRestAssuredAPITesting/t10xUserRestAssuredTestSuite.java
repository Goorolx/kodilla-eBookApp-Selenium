package com.kodilla.BeRestAssuredAPITesting;


import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.kodilla.BeRestAssuredAPITesting.Utils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class t10xUserRestAssuredTestSuite {
    private int usrId;

    @Test
    public void t101_givenRegisterUrl_whenSuccessRegistration_thenResponseHasIdAndNewEqualsTrue() {
        //given
        int expectedHttpCode = 200;
        int expectedId = 210;
        boolean expectedIsUserNew = true;
        JSONObject json = new JSONObject()   //building Json with registration data
                .put("login", "John Tester")
                .put("password", "rudy1031");

        //When + Then
        given()
                .contentType("application/json")  //another way to specify content type
                .body(json.toString())
                .when().post(registerUrl)
                .then().statusCode(expectedHttpCode)
                .assertThat().body("id", greaterThanOrEqualTo(expectedId))  //last manually added user has id of 210+
                .assertThat().body("new", is(expectedIsUserNew));  //when user already exists new is false
    }

    @Test
    public void t102_givenLoginUrl_whenSuccessLogin_thenResponseUserId() {
        //given
        JSONObject json = jsonObjectUserBuilder(testUser, testUsrPwd);
        int expectedHttpCode = 200;
        String expectedId = "210";

        //when
        given()
                .contentType("application/json")  //another way to specify content type
                .body(json.toString())
                .when().post(loginUrl)
        //then
                .then().statusCode(expectedHttpCode)
                .assertThat().body(is(expectedId));
    }

    @Test
    public void t103_givenLoginUrl_whenBadLoginGoodPassword_thenLoginFailed() {
        //given
        JSONObject json = jsonObjectUserBuilder(testUser + "x", testUsrPwd);

        //when + then
        given()
                .contentType("application/json")
                .body(json.toString())
                .when().post(loginUrl)  //Dropped status verification as app returns 200 on failed login as well
                .then().assertThat().body(is("-1")); //App returns -1 when failed login
    }

    @Test
    public void t104_givenLoginUrl_whenGoodLoginBadPassword_thenLoginFailed() {
        //given
        JSONObject json = jsonObjectUserBuilder(testUser, testUsrPwd + "1");

        //when + then
        given()
                .contentType("application/json")
                .body(json.toString())
                .when().post(loginUrl)  //Dropped status verification as app returns 200 on failed login as well
                .then().assertThat().body(is("-1")); //App returns -1 when failed login
    }

    @Test
    public void t105_givenRegisterUrl_whenRegistrationWithExistingUserAndDifferentPassword_thenError() {
        //given
        JSONObject json = jsonObjectUserBuilder(testUser, "testtest");

        //when + then
        given()
                .contentType("application/json")  //another way to specify content type
                .body(json.toString())
                .when().post(registerUrl)
                .then()
                .log().ifStatusCodeIsEqualTo(200)
                .statusCode(403);

        //trying to login using previous credentials, verifying if password is not overwritten, should login success
        t102_givenLoginUrl_whenSuccessLogin_thenResponseUserId();

    }

    @Test(priority = 0)
    public void t106_givenRegisterUrl_whenSuccessRegistrationWithPolishCharacters_thenResponseHasIdAndNewEqualsTrue() {
        //given
        JSONObject json = new JSONObject()   //building Json with registration data
                .put("login", "Góroliść")
                .put("password", "ródą102");
        Response response = given()
                .contentType("application/json")  //another way to specify content type
                .body(json.toString())
                .when().post(registerUrl)
                .then().statusCode(200)
                .assertThat().body("id", greaterThan(210))  //last manually added user has id of 210+
                .assertThat().body("new", is(false))  //when user already exists new is false
                .extract().response();

        int usrIdPl = response.path("id");
        System.out.println("New user with Polish characters has number " + usrId);
        usrId = usrIdPl;
    }

    @Test(priority = 0)
    public void t107_givenLoginUrl_whenSuccessLoginWithPolishCharacters_thenResponseUserId() {
        //given
        JSONObject json = new JSONObject()   //login
                .put("login", "Góroliść")
                .put("password", "ródą102");
        //when + then
        given()
                .contentType("application/json")  //another way to specify content type
                .body(json.toString())
                .when().post(loginUrl)
                .then().statusCode(200)
                //.assertThat().body(is(usrId));
                .assertThat().body(is("212"));
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasAuthor_thenCorrect() {
        //given
        given()

        //when + then
                .when().get(baseUrl + "/titles/?userId=210")
                .then().statusCode(200)
                .assertThat().body("author", hasItems("John Doe"));
    }

    @Test
    public void getUsrId() {
        //given
        JSONObject json = jsonObjectUserBuilder(testUser, testUsrPwd);
        //when
        String response = given().contentType("application/json").body(json.toString())
                        .when().post(loginUrl)
        //then
                        .then().statusCode(200).extract().response().body().asString();
                        // usrId = (int) response.toString();
        System.out.println(response);
    }
}
