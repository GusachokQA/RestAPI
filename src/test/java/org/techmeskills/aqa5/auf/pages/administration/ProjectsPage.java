package org.techmeskills.aqa5.auf.pages.administration;

import org.openqa.selenium.By;
import org.techmeskills.aqa5.auf.baseEntity.BasePage;
import org.techmeskills.aqa5.auf.core.BrowsersService;
import org.techmeskills.aqa5.auf.elements.UIElement;

public class ProjectsPage extends BasePage {
    protected By confirmationYesCheckboxSelector = By.cssSelector("#dialog-ident-deleteDialog input");
    protected By confirmationOkButtonSelector = By.cssSelector("#dialog-ident-deleteDialog .button-ok");
    protected By messageSuccessSelector = By.className("message-success");

    public ProjectsPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(browsersService.getBaseUrl() + "index.php?/admin/projects/overview");
    }

    public boolean isPageOpened() {
        return browsersService.getDriver().getTitle().equalsIgnoreCase("Projects - TestRail");
    }

    public UIElement getProjectItemLink(String projectName) {
        return new UIElement(browsersService, By.linkText(projectName));
    }

    public UIElement getDeleteIcon(String projectName) {
        UIElement element = getProjectItemLink(projectName);
        UIElement parent = element.getParent().getParent();
        return parent.findElement(By.className("icon-small-delete"));
    }

    public UIElement getConfirmationYesCheckbox() {
        return new UIElement(browsersService, confirmationYesCheckboxSelector);
    }

    public UIElement getConfirmationOkButton() {
        return new UIElement(browsersService, confirmationOkButtonSelector);
    }

    public String getMessageSuccessText() { return new UIElement(browsersService, messageSuccessSelector).getText(); }
}
