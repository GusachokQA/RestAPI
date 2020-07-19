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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class apiTest4 extends BaseApiTest {
    int projectID;

    @Test
    public void getAllUsers1() {
        String endpoint = "/index.php?/api/v2/get_users";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllUsers2() {
        String endpoint = "/index.php?/api/v2/get_users";

        User user = new User.Builder()
                .withName("AQA5 Master")
                .withEmail("atrostyanko+master@gmail.com")
                .withIsActive(true)
                .build();

        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .body("get(0).name", is(user.getName()))
                .body("get(0).email", equalTo(user.getEmail()))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllProjects() {
        String endpoint = "/index.php?/api/v2/get_projects";

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
                .withName("PR01")
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
                .withName("PR02")
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
        project.setName("PR03");
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
                .withName("PR04")
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
                .withName("PR04_UPD")
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
