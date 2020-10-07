package com.kodilla.BeRestAssuredAPITesting;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.kodilla.BeRestAssuredAPITesting.Utils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class t20xTitleRestAssuredTestSuite {
    String testTitle;
    String testAuthor;
    int testYear;
    int testTitleId;

    @Test(priority = 0)
    public void t201_givenTitleUrl_whenPostNewTitle_thenResponseHasTitleId() {
        //given
        testTitle = "War and Peace";
        testAuthor = "Lew Tołstoj";
        int testYear = 1869;
        int expStatus = 200;
        int oldTestId = 1020; //Id 1020 was returned when tested manually
        //building Json object
        String jsonString = jsonStringTitleBuilder(testUsrId, testTitle, testAuthor, testYear);

        //when
        Response response = given()
                .contentType("application/json")
                .body(jsonString)
                .when()
                .post(titleUrl)
                //then
                .then().statusCode(expStatus)
                .extract().response();

//passing titleId as integer for assertion and following tests
        testTitleId = Integer.parseInt(response.getBody().asString());
        Assert.assertTrue(testTitleId > oldTestId);
        System.out.println(testTitleId);
    }

    @Test
    public void t202_givenTitleUrl_whenGetTitlesList_thenResponseHasTitleFromt201() {
        //given
        testTitle = "War and Peace";
        testAuthor = "Lew Tołstoj";
        int testYear = 1869;

        //when + then
        given().queryParam("userId", testUsrId)
                .when()
                .get(titleUrl)
                .then().statusCode(200)
                .assertThat()
                .body("id", hasItem(testTitleId))  //created in t201
                .body("title", hasItem(testTitle))
                .body("author", hasItem(testAuthor))
                .body("year", hasItem(testYear));
    }

    @Test
    public void t203a_givenTitleUrl_whenPostNewTitleWithEmptyTitleField_thenFa() {
        //given
        String jsonString = jsonStringTitleBuilder(210, "", "John Doe", 1999);

        //when + then
        given().contentType("application/json")
                .body(jsonString)
                .post(titleUrl)
                .then().statusCode(greaterThan(300)); // grater than 300 because all success responses are within range 200-299
    }

    @Test
    public void t203b_givenTitleUrl_whenPostNewTitleWithEmptyAuthorField_thenFail() {
        //given
        String jsonString = jsonStringTitleBuilder(210, "Random Title", "", 1999);
        //when + then
        given().contentType("application/json")
                .body(jsonString)
                .post(titleUrl)
                .then().statusCode(greaterThan(300)); //not success http response
    }

    @Test
    public void t203c_givenTitleUrl_whenPostNewTitleWithEmptyYearField_thenFail() {
        //given
        String jsonString = jsonStringTitleBuilder(210, "Random Title", "John Doe", 0);

        //when + then
        given().contentType("application/json")
                .body(jsonString)
                .post(titleUrl)
                .then().statusCode(greaterThan(300));
        //TODO think about this one, how to force missing year? possible?
    }

    @Test(priority = 1)
    public void t204_givenTitleUrl_whenPutTitle_thenEditedSuccess() {
        //given
        String testTitle = "Kalarepa";
        String testAuthor = "Ann Marchew";
        int testYear = 1008;
        int titleId = testTitleId;
        JSONObject json = new JSONObject()
                .put("userId", testUsrId)
                .put("id", titleId)
                .put("title", testTitle)
                .put("author", testAuthor)
                .put("year", testYear);
        int expStatus = 200;

        //when
        given().contentType("application/json")
                .body(json.toString())
                .when().put(titleUrl)
                //then
                .then().statusCode(expStatus)
                .assertThat()
                .body("id", is(titleId))
                .body("title", is(testTitle))
                .body("author", is(testAuthor))
                .body("year", is(testYear));
    }

    @Test
    public void t205_givenTitleUrl_whenDeleteTitle_thenResponseTrue() {
        //given
        String expResponse = "true";
        //int titleIdToRemove = testTitleId;
        int titleIdToRemove = 1022;
        int expStatus = 200;

        //when
        given().queryParam("userId", testUsrId)
                .queryParam("id", titleIdToRemove)
                .when().delete(titleUrl)
                //then
                .then().statusCode(expStatus)
                .assertThat().body(is(expResponse));

        //when + then   //part of t202
        given().queryParam("userId", testUsrId)
                .when()
                .get(titleUrl)
                .then().statusCode(200)
                .assertThat()
                .body("id", not(titleIdToRemove));
       // TODO this assertion is always true, I have to think about it
    }


}
