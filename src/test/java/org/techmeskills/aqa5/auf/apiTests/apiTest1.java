package org.techmeskills.aqa5.auf.apiTests;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class apiTest1 {

    @Test
    public void test() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }
}
