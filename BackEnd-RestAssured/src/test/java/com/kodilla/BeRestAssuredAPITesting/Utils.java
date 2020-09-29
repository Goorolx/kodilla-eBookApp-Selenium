package com.kodilla.BeRestAssuredAPITesting;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public final class Utils {
    public static final String baseUrl = "https://ta-ebookrental-be.herokuapp.com";
    public static final String registerUrl = "https://ta-ebookrental-be.herokuapp.com/user/register";
    public static final String loginUrl = "https://ta-ebookrental-be.herokuapp.com/user/login";

    public static final String testUser = "John Tester";
    public static final String testUsrPwd = "rudy1031";

    @DataProvider(name="loginDataFail")
    public static Object [][] createTestData(){
        return new Object [][]{
                {"Zbigniew","kolo201"},
        {"CZeslaww","dzoi00990"},
        };

    }

    public static JSONObject jsonObjectUserBuilder(String login, String pwd){
        JSONObject json = new JSONObject()
                .put("login",login)
                .put("password", pwd);
        return json;
    }


}
