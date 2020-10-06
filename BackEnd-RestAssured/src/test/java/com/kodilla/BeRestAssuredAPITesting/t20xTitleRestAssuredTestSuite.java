package com.kodilla.BeRestAssuredAPITesting;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.kodilla.BeRestAssuredAPITesting.Utils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class t20xTitleRestAssuredTestSuite {

    @Test
    public void t201_givenTitleUrl_whenPostNewTitle_thenResponseHasTitleId() {
        //given
        String jsonString = jsonStringTitleBuilder(210, "War and Peace", "Lew Tołstoj", 1869);

        //when
        given()
                .contentType("application/json")
                .body(jsonString)
                .when()
                .post(titleUrl)
                //then
                .then().statusCode(200)
                .assertThat().body(greaterThanOrEqualTo(405));  //Id 405 was returned when tested manually
    }

    @Test
    public void t202_givenTitleUrl_whenGetTitlesList_thenResponseHasTitleFromt201() {
        //given
        given().queryParam("userId", "210")

                //when + then
                .when()
                .get(titleUrl)
                .then().statusCode(200)
                .assertThat().body(hasItems("War and Peace", "Lew Tołstoj", "1869"));
//TODO fix assertion above
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

    @Test
    public void t204_givenTitleUrl_whenPutTitle_thenEditedSuccess(){
        //given
        String testTitle = "Kalarepa";
        String testAuthor = "Ann Marchew";
        int testYear = 1008;
        int titleId = 406;
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


}
