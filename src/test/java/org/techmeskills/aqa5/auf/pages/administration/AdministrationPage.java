package org.techmeskills.aqa5.auf.pages.administration;

import org.openqa.selenium.By;
import org.techmeskills.aqa5.auf.baseEntity.BasePage;
import org.techmeskills.aqa5.auf.core.BrowsersService;
import org.techmeskills.aqa5.auf.elements.UIElement;

public class AdministrationPage extends BasePage {
    protected By projectLinkSelector = By.xpath("//div[@class = 'info-box-title']/a[. = 'Projects']");

    public AdministrationPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(browsersService.getBaseUrl() + "index.php?/admin/overview");
    }

    public boolean isPageOpened() {
        return new UIElement(browsersService, By.xpath("//h1[. = 'Administration']")).isDisplayed();
    }

    public UIElement getProjectsLink() {
        return new UIElement(browsersService, projectLinkSelector);
    }
}
