package com.as.restapi.tests;


import com.as.restapi.utils.HelperMethods;
import com.as.restapi.utils.RestUtil;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) //For Ascending order test execution
public class UseCase1Test {

    //First, I declared Response and JsonPath objects.
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object

    private String employeeBody = "{\n" +
            "\t\"name\": \"Lisa Rogic\",\n" +
            "\t\"salary\": \"20000\",\n" +
            "\t\"Children\":[{\"ChildName\" : \"Filip\" },{\"ChildName\" : \"Petra\"}],\n" +
            "\t\"address\": { \n" +
            "                \"street\": \"Kulas Light\",\n" +
            "                \"suite\": \"Apt. 556\",\n" +
            "                \"city\": \"Gwenborough\",\n" +
            "                \"zipcode\": \"92998-3874\",\n" +
            "                \"geo\": {\n" +
            "\t\t\t\t\"lat\": \"-37.3159\",\n" +
            "\t\t\t\t\"lng\": \"81.1496\"\n" +
            "                 }\n" +
            "\t\t\t\t}\n" +
            "}";


    /*
    Second, we should do setup operations, get JSON response from the API and put it into JsonPath object
    Then we will do query and manipulations with JsonPath class’s methods.
    We can do all of the preparation operations after @Before Junit annotation.
    */
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

    @Test
    public void TC01_get_test() {

        res.then().body("id", hasItems(1, 2));
        res.then().body("name", hasItems("Pankaj"));
    }


    @Test
    public void TC02_post_test() {
        res = RestUtil.postResponse(employeeBody, "create");
        // tests
        res.then().body("id", Matchers.any(Integer.class));
        res.then().body("name", is("Lisa Rogic"));
    }


    @Test
    public void TC03_put_test() {

        res = RestUtil.putResponse("{\"name\": \"Lisa Tamaki\",\"salary\": \"20000\"}", "update/2");

        res.then().body("id", is(2));
        res.then().body("name", is("Lisa Tamaki"));
        res.then().body("salary", is("20000"));
    }


    @Test
    public void TC04_delete_test() {

        res = RestUtil.deleteResponse("delete/3");
        // tests
        res.then().body("id", not(3));
    }


    @Test
    public void TC05_verifyValuesTest() {
        //Verify the response contained the relevant value
        System.out.println(HelperMethods.getIds(jp).get(0).toString());
        Assert.assertEquals("Name is wrong!", ("Lisa Rogic"), jp.get("name[2]"));

        //https://goessner.net/articles/JsonPath/
        //System.out.println(jp.get("address[0].geo.lat"));
        Assert.assertEquals("City is wrong!", ("Gwenborough"), jp.get("address[2].city"));
        Assert.assertEquals("Latitude is wrong!", ("-37.3159"), jp.get("address[2].geo.lat"));
    }


    @Test
    public void TC06_verifyNumberOfEmployees() {
        //Verify how much employees were returned
        //Assert.assertEquals("Number of employees  is not equal to 3", 3, HelperMethods.getIdList(jp).size());
        System.out.println("Number of employees is equal to: " + HelperMethods.getIdList(jp).size());
    }


    @Test
    public void TC07_verifyContainsString() {
        //Verify the response contained the relevant string
        String suite = jp.get("address[2].suite");
        String zipcode = jp.get("address[2].zipcode");

        assertThat(suite, containsString("Apt"));
        assertThat(zipcode, is("92998-3874"));
    }


    @After
    public void afterTest() {
        //Reset Values
        RestUtil.resetBaseURI();
        RestUtil.resetBasePath();
    }

}