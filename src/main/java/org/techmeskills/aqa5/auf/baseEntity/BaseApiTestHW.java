package org.techmeskills.aqa5.auf.baseEntity;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.techmeskills.aqa5.auf.core.ReadPropertiesApi;
import org.techmeskills.aqa5.auf.utils.Listener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static io.restassured.RestAssured.given;

@Listeners(Listener.class)
public abstract class BaseApiTestHW {
    public ReadPropertiesApi properties;

    @BeforeClass
    public void setup() {
        properties = new ReadPropertiesApi();

        RestAssured.baseURI = properties.getURL();
        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
        System.out.println("Setup is done!");
    }
}