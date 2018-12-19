package com.as.restapi.tests;


import com.as.restapi.utils.HelperMethods;
import com.as.restapi.utils.RestUtil;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) //For Ascending order test execution
public class UseCase2Test {

    //First, declare Response and JsonPath objects.
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object


    @Before
    public void setup() {
        //Test Setup
        RestUtil.setBaseURI("http://localhost:7000/"); //Setup Base URI
        RestUtil.setBasePath("/employees"); //Setup Base Path
        RestUtil.setContentType(ContentType.JSON); //Setup Content Type
        RestUtil.createPath("list");
        res = RestUtil.getResponse(); //Get response
        jp = RestUtil.getJsonPath(res); //Get JsonPath
    }

    @Test
    public void TC00_StatusCodeTest() {
        //Verify the http response status returned. Check Status Code is 200?
        HelperMethods.checkStatusIs200(res);
    }


    @After
    public void afterTest() {
        //Reset Values
        RestUtil.resetBaseURI();
        RestUtil.resetBasePath();
    }

}