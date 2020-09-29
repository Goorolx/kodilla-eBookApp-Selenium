package com.kodilla.BeRestAssuredAPITesting;

import org.testng.annotations.Test;

import static com.kodilla.BeRestAssuredAPITesting.Utils.jsonStringTitleBuilder;
import static com.kodilla.BeRestAssuredAPITesting.Utils.titleUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class t20xTitleRestAssuredTestSuite {

    @Test
    public void t201_givenTitleUrl_whenPostNewTitle_thenResponseHasTitleId(){
        given()
                .contentType("application/json")
                .body(jsonStringTitleBuilder(210,"War and Peace","Lew Tołstoj",1869))
                .when()
                .post(titleUrl)
                .then().statusCode(200)
                .assertThat().body(greaterThanOrEqualTo(405));  //Id 405 was returned when tested manually
    }

    @Test
    public void t202_givenTitleUrl_whenGetTitlesList_thenResponseHasTitleFromt201(){
        given().queryParam("userId","210")
                .when()
                .get(titleUrl)
                .then().statusCode(200)
                .assertThat().body(hasItems("War and Peace","Lew Tołstoj","1869"));
//TODO fix assertion above
    }

}
