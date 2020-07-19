package org.techmeskills.aqa5.auf.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class apiMainTest {

    @Test
    public void test(){
        String baseUrl = "https://reqres.in";
        String endpoint = "/api/users/2";

        RestAssured.baseURI = baseUrl;

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, endpoint);

        int statusCode = response.getStatusCode();
        System.out.println("status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("body: " + responseBody);

        Assert.assertEquals(statusCode, 200, "invalid status code");
    }
}
