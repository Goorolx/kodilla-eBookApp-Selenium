package com.kodilla.BeRestAssuredAPITesting;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public final class Utils {
    //endpoints
    public static final String baseUrl = "https://ta-ebookrental-be.herokuapp.com";
    public static final String registerUrl = baseUrl + "/user/register";
    public static final String loginUrl = baseUrl + "/user/login";
    public static final String titleUrl = baseUrl + "/titles/";

    //Test User details
    public static final String testUser = "John Tester";
    public static final String testUsrPwd = "rudy1031";
    public static final int testUsrId = 210;

    //Title details
    public static final int titleId = 405;

    //Item details
    public static final int itemId = 408;

    //Rent details
    public static final int rentId = 410;


    //Data Provider not used yet
    @DataProvider(name = "loginDataFail")
    public static Object[][] createTestData() {
        return new Object[][]{
                {"Zbigniew", "kolo201"},
                {"CZeslaww", "dzoi00990"},
        };

    }

    //Building JSON object for User Endpoint testing
    public static JSONObject jsonObjectUserBuilder(String login, String pwd) {
        JSONObject json = new JSONObject()
                .put("login", login)
                .put("password", pwd);
        return json;
    }

    //Building JSON as String, used for Title endpoint testing
    public static String jsonStringTitleBuilder(int userId, String title, String author, int year) {
        JSONObject json = new JSONObject()
                .put("userId", userId)
                .put("title", title)
                .put("author", author)
                .put("year", year);
        return json.toString();
    }


}
