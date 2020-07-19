package org.techmeskills.aqa5.auf.apiTests;


import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;

import org.techmeskills.aqa5.auf.baseEntity.BaseApiTest;
import org.techmeskills.aqa5.auf.models.Project;
import org.techmeskills.aqa5.auf.models.ProjectSimple;
import org.techmeskills.aqa5.auf.models.ProjectTypes;
import org.techmeskills.aqa5.auf.models.User;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class apiMainTest4 extends BaseApiTest {
    int projectID;

    @Test
    public void getAllUsers(){
        String endpoint = "index.php?/api/v2/get_users";
        User user = master;

        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .body("get(0).name", is(user.getName()))
                .body("get(0).email", equalTo(user.getEmail()))
                .body("get(0).is_active", is(user.isActive()))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getUser(){
        String endpoint = "index.php?/api/v2/get_user/2";
        User user = tester;

        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .body("name", is(user.getName()))
                .body("email", equalTo(user.getEmail()))
                .body("is_active", is(user.isActive()))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllProjects(){
        String endpoint = "index.php?/api/v2/get_projects";


        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProject1() {
        String endpoint = "/index.php?/api/v2/add_project";

        Project project = new Project.Builder()
                .withName("PR01_V")
                .withType(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        given()
                .body(String.format("{\n" +
                        "    \"name\": \"%s\",\n" +
                        "    \"suite_mode\": %d\n" +
                        "}", project.getName(), project.getSuiteMode()))
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProject2() {
        String endpoint = "/index.php?/api/v2/add_project";

        Project project = new Project.Builder()
                .withName("PR02_V")
                .withType(ProjectTypes.MULTIPLE_SUITE_MODE)
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", project.getName());
        jsonAsMap.put("suite_mode", project.getSuiteMode());

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProject3() {
        String endpoint = "/index.php?/api/v2/add_project";

        ProjectSimple project = new ProjectSimple();
        project.setName("PR03_V");
        project.setSuite_mode(ProjectTypes.MULTIPLE_SUITE_MODE);

        given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProject4() {
        String endpoint = "/index.php?/api/v2/add_project";

        Project project = new Project.Builder()
                .withName("PR04_V")
                .withType(ProjectTypes.SINGLE_SUITE_BASELINES)
                .build();

        projectID = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addProject4")
    public void updateProject1() {
        String endpoint = "index.php?/api/v2/update_project/{project_id}";

        Project project = new Project.Builder()
                .withName("PR04_UPD_V")
                .withAnnouncement("Test!!!")
                .withIsShowAnnouncement(true)
                .withIsCompleted(true)
                .build();

        given()
                .pathParam("project_id", projectID)
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "updateProject1")
    public void deleteProject1() {
        String endpoint = "index.php?/api/v2/delete_project/{project_id}";

        given()
                .pathParam("project_id", projectID)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
