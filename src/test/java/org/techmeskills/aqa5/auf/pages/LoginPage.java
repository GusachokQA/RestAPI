package org.techmeskills.aqa5.auf.pages;

import org.openqa.selenium.By;
import org.techmeskills.aqa5.auf.baseEntity.BasePage;
import org.techmeskills.aqa5.auf.core.BrowsersService;
import org.techmeskills.aqa5.auf.elements.UIElement;

public class LoginPage extends BasePage {
    private String URL = "https://aqa5master.testrail.io/";

    private By emailSelector = By.id("name");
    private By passwordSelector = By.id("password");
    private By loginSelector = By.id("button_primary");

    public LoginPage(BrowsersService browsersService) {
        super(browsersService, false);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        return new UIElement(browsersService, By.id("button_primary")).isDisplayed();
    }

    public UIElement getEmailField() {
        return new UIElement(browsersService, emailSelector);
    }

    public UIElement getPasswordField() {
        return new UIElement(browsersService, passwordSelector);
    }

    public UIElement getLoginButton() {
        return new UIElement(browsersService, loginSelector);
    }
}
