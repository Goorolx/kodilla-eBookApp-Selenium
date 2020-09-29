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
    @Test
    public void t203a_givenTitleUrl_whenPostNewTitleWithEmptyTitleField_thenFa(){
        given().contentType("application/json")
                .body(jsonStringTitleBuilder(210,"","John Doe",1999))
                .post(titleUrl)
                .then().statusCode(greaterThan(300)); // grater than 300 because all success responses are within range 200-299
    }
    @Test
    public void t203b_givenTitleUrl_whenPostNewTitleWithEmptyAuthorField_thenFail(){
        given().contentType("application/json")
                .body(jsonStringTitleBuilder(210,"Random Title","",1999))
                .post(titleUrl)
                .then().statusCode(greaterThan(300));
    }
    @Test
    public void t203c_givenTitleUrl_whenPostNewTitleWithEmptyYearField_thenFail(){
        given().contentType("application/json")
                .body(jsonStringTitleBuilder(210,"Random Title","John Doe",0))
                .post(titleUrl)
                .then().statusCode(greaterThan(300));
        //TODO think about this one
    }


}
