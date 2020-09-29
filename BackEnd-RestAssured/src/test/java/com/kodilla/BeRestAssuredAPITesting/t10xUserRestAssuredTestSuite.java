package com.kodilla.BeRestAssuredAPITesting;


import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.kodilla.BeRestAssuredAPITesting.Utils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class t10xUserRestAssuredTestSuite {
    private int usrId ;

    @Test
    public void t101_givenUrl_whenSuccessRegistration_thenResponseHasIdAndNewEqualsTrue() {
        JSONObject json = new JSONObject()   //building Json with registration data
                .put("login", "John Tester")
                .put("password", "rudy1031");
        given().with()
                .contentType("application/json")  //another way to specify content type
                .body(json.toString())
                .when().post(registerUrl)
                .then().statusCode(200)
                .assertThat().body("id", greaterThan(210))  //last manually added user has id of 210+
                .assertThat().body("new", is(true));  //when user already exists new is false
    }

    @Test
    public void givenLoginUrl_whenSuccessLogin_thenResponseUserId(){
        JSONObject json = jsonObjectUserBuilder(testUser,testUsrPwd);
        given().with()
                .contentType("application/json")  //another way to specify content type
                .body(json.toString())
                .when().post(loginUrl)
                .then().statusCode(200)
                .assertThat().body( is("210"));
    }



    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasAuthor_thenCorrect() {
        given()
                .when().get(baseUrl + "/titles/?userId=210")
                .then().statusCode(200)
                .assertThat().body("author", hasItems("John Doe"));
    }

    @Test
    public void getRequest() {
        given()
                .when().get(baseUrl + "/titles/?userId=210")
                .then().assertThat().statusCode(200);

    }
}
