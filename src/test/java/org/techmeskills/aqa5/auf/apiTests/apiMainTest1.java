package org.techmeskills.aqa5.auf.apiTests;


import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class apiMainTest1 {

    @Test
    public void test(){
        given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth()
                .preemptive().basic("atrostyanko+master@gmail.com", "QqtRK9elseEfAk6ilYcJ")
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }
}
