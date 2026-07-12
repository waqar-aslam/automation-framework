package com.framework.testcases;

import com.framework.base.BaseClass;
import com.framework.config.ConfigReader;
import com.framework.pages.LoginPage;
import com.framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;
    private ConfigReader config;

    @Test(priority = 1, description = "Verify login with valid credentials")
    public void testValidLogin() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testValidLogin");
        loginPage = new LoginPage(driver);
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page not loaded properly");

        homePage = loginPage.login(username, password);

        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page not loaded after login");
        Assert.assertEquals(homePage.getPageTitle(), "Products", "Page title mismatch");
        logger.info("Test passed successfully!");
    }

    @Test(priority = 2, description = "Verify login with invalid credentials")
    public void testInvalidLogin() {
        config = new ConfigReader();
        String username = config.getProperty("invalid.username");
        String password = config.getProperty("invalid.password");

        logger.info("Starting testInvalidLogin");
        loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Epic sadface"), "Error message not displayed");
        logger.info("Error message displayed: {}", errorMsg);
    }
}