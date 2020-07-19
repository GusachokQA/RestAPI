package org.techmeskills.aqa5.auf.apiTestsHomework;

import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;
import org.techmeskills.aqa5.auf.baseEntity.BaseApiTestHW;
import org.techmeskills.aqa5.auf.models.ProjectApi;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class apiTest extends BaseApiTestHW {

    @Test
    public void getListUsers() {
        String endpoint = "/api/users?page=2";

        given()
        .when()
                .get(endpoint)
        .then()
                .log().body()
                .body("page", is(2))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleUser() {
        String endpoint = "/api/users/2";

        given()
        .when()
                .get(endpoint)
        .then()
                .log().body()
                .body("data.id", is(2))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleUserNotFound() {
        String endpoint = "/api/users/23";

        given()
        .when()
                .get(endpoint)
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getListResource() {
        String endpoint = "/api/unknown";

        given()
        .when()
                .get(endpoint)
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleResource() {
        String endpoint = "/api/unknown/2";

        given()
        .when()
                .get(endpoint)
        .then()
                .log().body()
                .body("data.id", is(2))
                .body("data.name", is("fuchsia rose"))
                .body("data.color", is("#C74375"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleResourceNotFound() {
        String endpoint = "/api/unknown/23";

        given()
        .when()
                .get(endpoint)
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void postCreate() {
        String endpoint = "/api/users";

        ProjectApi project = new ProjectApi.Builder()
                .withName("morpheus")
                .withJob("leader")
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", project.getName());
        jsonAsMap.put("job", project.getJob());

        given()
                .body(jsonAsMap)
        .when()
                .post(endpoint)
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void putUpdate() {
        String endpoint = "/api/users/2";

        ProjectApi project = new ProjectApi.Builder()
                .withName("morpheus")
                .withJob("zion resident")
                .build();

        given()
                .body(project, ObjectMapperType.GSON)
        .when()
                .put(endpoint)
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void patchUpdate() {
        String endpoint = "/api/users/2";

        ProjectApi project = new ProjectApi.Builder()
                .withName("morpheus")
                .withJob("zion resident")
                .build();

        given()
                .body(project, ObjectMapperType.GSON)
        .when()
                .put(endpoint)
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void delete() {
        String endpoint = "/api/users/2";

        given()
        .when()
                .delete(endpoint)
         .then()
                .log().body()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void postRegisterSuccessful() {
        String endpoint = "/api/register";

        ProjectApi project = new ProjectApi.Builder()
                .withEmail("eve.holt@reqres.in")
                .withPassword("pistol")
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", project.getEmail());
        jsonAsMap.put("password", project.getPassword());

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postRegisterUnsuccessful() {
        String endpoint = "/api/register";

        ProjectApi project = new ProjectApi.Builder()
                .withEmail("sydney@fife")
                .build();

        given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("error", is("Missing password"))
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void postLoginSuccessful() {
        String endpoint = "/api/login";

        ProjectApi project = new ProjectApi.Builder()
                .withEmail("eve.holt@reqres.in")
                .withPassword("cityslicka")
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", project.getEmail());
        jsonAsMap.put("password", project.getPassword());

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postLoginUnsuccessful() {
        String endpoint = "/api/login";

        ProjectApi project = new ProjectApi.Builder()
                .withEmail("peter@klaven")
                .build();

        given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("error", is("Missing password"))
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void getDelayedResponse() {
        String endpoint = "/api/users?delay=3";

        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .body("page", is(1))
                .statusCode(HttpStatus.SC_OK);
    }
}
