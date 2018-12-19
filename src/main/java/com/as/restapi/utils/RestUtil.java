package com.as.restapi.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class RestUtil {
    //Global Setup Variables
    public static String path; //Rest request path

    /*
    ***Sets Base URI***
    Before starting the test, we should set the RestAssured.baseURI
    */
    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
      //  System.out.print("baseURI: " + baseURI + "\n");
    }

    /*
    ***Sets base path***
    Before starting the test, we should set the RestAssured.basePath
    */
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
      //  System.out.print("basePath: " + basePath + "\n");
    }

    /*
    ***Reset Base URI (after test)***
    After the test, we should reset the RestAssured.baseURI
    */
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    /*
    ***Reset base path (after test)***
    After the test, we should reset the RestAssured.basePath
    */
    public static void resetBasePath() {
        basePath = null;
    }

    /*
    ***Sets ContentType***
    We should set content type as JSON or XML before starting the test
    */
    public static void setContentType(ContentType Type) {
        given().contentType(Type);
    }

    /*
    ***search query path of first example***
    It is  equal to "barack obama/videos.json?num_of_videos=4"
    */
    public static void createSearchQueryPath(String searchTerm, String jsonPathTerm, String param, String paramValue) {

        path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
//        System.out.print("path: " + path +"\n");
    }


    public static void createPath(String endpoint) {
        path = "/" + endpoint;
      //  System.out.print("path: " + path +"\n");
    }

    /*
    ***Returns response***
    We send "path" as a parameter to the Rest Assured'a "get" method
    and "get" method returns response of API
    */
    public static Response getResponse() {
        return get(path);
    }


    /*
     ***Returns Response object***
     * POST Method
     */
    public static Response postResponse(String body, String path) {
        Response response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(path);
      //  System.out.println("POST Response\n" + response.asString());
        return response;
    }


    /*
     ***Returns Response object***
     * PUT Method
     */
    public static Response putResponse(String body, String path) {
        Response response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .put(path);
     //   System.out.println("PUT Response\n" + response.asString());
        return response;
    }


    /*
     ***Returns Response object***
     * DELETE Method
     */
    public static Response deleteResponse(String path) {
        Response response = delete(path);
     //   System.out.println("DELETE Response\n" + response.asString());
        return response;
    }


    /*
     ***Returns JsonPath object***
     * First convert the API's response to String type with "asString()" method.
     * Then, send this String formatted json response to the JsonPath class and return the JsonPath
     */
    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
//        System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }

/*
    If you are using default authentication from JIRA, then preemptive authentication is what you need. Below are the sample code.
    RestAssured.baseURI = System.getProperty("baseurl");
    PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    authScheme.setUserName("admin");
    authScheme.setPassword("admin");
    RestAssured.authentication = authScheme;
*/


    /*
     ***Returns Response for GET with Header***
     *
     */
    public static Response getWithHeaderResponse(String body, String userId, String userSessionToken, String path) {
        Header h1 = new Header("UID", userId + ":simulatorUUID");
        Header h2 = new Header("Authorization", "Token " + userSessionToken);
        Response response = given()
                .header(h1)
                .header(h2)
                .when().get(path);
        return response;
    }

    /*
     ***Returns Response for POST with Header***
     *
     */
    public static Response postWithHeaderResponse(String body, String userId, String userSessionToken, String path) {
        Header h1 = new Header("UID", userId + ":simulatorUUID");
        Header h2 = new Header("Authorization", "Token " + userSessionToken);
        Response response = given()
                .header(h1)
                .header(h2)
                .body(body)
                .when().post(path);
        return response;
    }
}