package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement appLogo;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        logger.info("Entering username: {}", username);
        enterText(usernameField, username);
        return this;
    }

    @Step("Enter password: {password}")
    public LoginPage enterPassword(String password) {
        logger.info("Entering password: {}", password);
        enterText(passwordField, password);
        return this;
    }

    @Step("Click on Login button")
    public HomePage clickLoginButton() {
        logger.info("Clicking on Login button");
        clickElement(loginButton);
        return new HomePage(driver);
    }

    @Step("Perform login with username: {username} and password: {password}")
    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    @Step("Validate login error message")
    public String getErrorMessage() {
        logger.info("Getting error message");
        return getText(errorMessage);
    }

    @Step("Verify login page loaded successfully")
    public boolean isLoginPageLoaded() {
        logger.info("Verifying login page loaded");
        return isElementDisplayed(appLogo) && isElementDisplayed(usernameField);
    }
}