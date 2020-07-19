package org.techmeskills.aqa5.auf.uiTests;

import org.techmeskills.aqa5.auf.baseEntity.BaseTest;
import org.techmeskills.aqa5.auf.pages.administration.ProjectsPage;
import org.techmeskills.aqa5.auf.steps.AdministrationStep;
import org.techmeskills.aqa5.auf.steps.LoginStep;
import org.techmeskills.aqa5.auf.steps.ProjectStep;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest2 extends BaseTest {
    @Test
    public void login() {
        LoginStep loginStep = new LoginStep(browsersService);
        loginStep.login("atrostyanko+master@gmail.com", "QqtRK9elseEfAk6ilYcJ");
    }

    @Test(dependsOnMethods = "login")
    public void createProject() {
        ProjectStep projectStep = new ProjectStep(browsersService);
        projectStep.createNewProject("PR02", "Use a single repository with baseline support");

        Assert.assertTrue(browsersService.getDriver().getTitle().equalsIgnoreCase("PR02 - TestRail")
                || browsersService.getDriver().getTitle().equalsIgnoreCase("Projects - TestRail"));  ;
    }

    @Test(dependsOnMethods = "createProject")
    public void deleteProject() {
        AdministrationStep administrationStep = new AdministrationStep(browsersService);
        administrationStep.deleteProject("PR02");

        ProjectsPage projectsPage = new ProjectsPage(browsersService, false);
        Assert.assertEquals(projectsPage.getMessageSuccessText(), "Successfully deleted the project.");
    }
}
