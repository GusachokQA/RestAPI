package org.techmeskills.aqa5.auf.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class apiTest {

    @Test
    public void test() {
        // Setup RestAssured
        RestAssured.baseURI = "https://reqres.in";
        String endpoint = "/api/users/2";

        // Setup Request Object
        RequestSpecification httpRequest = RestAssured.given();

        // Setup Response Object
        Response response = httpRequest.request(Method.GET, endpoint);

        // Get Response Body
        String responseBody = response.getBody().asString();
        System.out.println("Response: " + responseBody);

        // Get Response Status
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
