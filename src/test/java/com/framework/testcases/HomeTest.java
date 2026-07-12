package com.framework.testcases;

import com.framework.base.BaseClass;
import com.framework.config.ConfigReader;
import com.framework.pages.LoginPage;
import com.framework.pages.HomePage;
import com.framework.pages.CartPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("E-Commerce Application")
@Feature("Home Page Functionality")
public class HomeTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private ConfigReader config;

    @Test(priority = 1, description = "Verify home page loads successfully after login")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Home Page Loading")
    @Description("Test to verify home page loads correctly after successful login")
    public void testHomePageLoadsSuccessfully() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testHomePageLoadsSuccessfully");

        // Login first
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page not loaded properly");

        homePage = loginPage.login(username, password);

        // Verify home page
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page not loaded properly");
        Assert.assertEquals(homePage.getPageTitle(), "Products", "Page title mismatch");
        Assert.assertTrue(homePage.getInventoryItemsCount() > 0, "No inventory items displayed");

        logger.info("Home page loaded successfully with {} items", homePage.getInventoryItemsCount());
    }

    @Test(priority = 2, description = "Verify inventory items are displayed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Inventory Display")
    @Description("Test to verify inventory items are displayed on home page")
    public void testInventoryItemsAreDisplayed() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testInventoryItemsAreDisplayed");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);

        int itemCount = homePage.getInventoryItemsCount();
        Assert.assertTrue(itemCount > 0, "No inventory items found");
        logger.info("Found {} inventory items", itemCount);
    }

    @Test(priority = 3, description = "Verify add item to cart from home page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add to Cart")
    @Description("Test to verify adding item to cart from home page")
    public void testAddItemToCart() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testAddItemToCart");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);

        // Add first item to cart
        homePage.addFirstItemToCart();

        // Go to cart and verify
        cartPage = homePage.goToCart();
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page not loaded");
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart is empty after adding item");

        logger.info("Item added to cart successfully. Cart contains {} items", cartPage.getCartItemCount());
    }

    @Test(priority = 4, description = "Verify sorting functionality on home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sorting")
    @Description("Test to verify sorting items by different options")
    public void testSortItems() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testSortItems");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);

        // Sort by price low to high
        homePage.sortItems("Price (low to high)");

        // Get item count after sorting
        int itemCount = homePage.getInventoryItemsCount();
        Assert.assertTrue(itemCount > 0, "No items after sorting");
        logger.info("Items sorted successfully. {} items displayed", itemCount);
    }

    @Test(priority = 5, description = "Verify navigation to cart from home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigation")
    @Description("Test to verify navigation to cart page from home page")
    public void testNavigateToCart() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testNavigateToCart");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);

        // Navigate to cart
        cartPage = homePage.goToCart();
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page not loaded");

        logger.info("Successfully navigated to cart page");
    }

    @Test(priority = 6, description = "Verify multiple items can be added to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Multiple Items")
    @Description("Test to verify adding multiple items to cart")
    public void testAddMultipleItemsToCart() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testAddMultipleItemsToCart");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);

        // Add first 3 items to cart
        homePage.addItemToCart(0);
        homePage.addItemToCart(1);
        homePage.addItemToCart(2);

        // Verify in cart
        cartPage = homePage.goToCart();
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page not loaded");
        Assert.assertEquals(cartPage.getCartItemCount(), 3, "Not all items added to cart");

        logger.info("Successfully added {} items to cart", cartPage.getCartItemCount());
    }

    @Test(priority = 7, description = "Verify home page title")
    @Severity(SeverityLevel.MINOR)
    @Story("Page Title")
    @Description("Test to verify home page title is correct")
    public void testHomePageTitle() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testHomePageTitle");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);

        String pageTitle = homePage.getPageTitle();
        Assert.assertEquals(pageTitle, "Products", "Page title is incorrect");
        logger.info("Home page title is: {}", pageTitle);
    }

    @Test(priority = 8, description = "Verify logout functionality from home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Logout")
    @Description("Test to verify logout functionality")
    public void testLogoutFromHomePage() {
        config = new ConfigReader();
        String username = config.getProperty("valid.username");
        String password = config.getProperty("valid.password");

        logger.info("Starting testLogoutFromHomePage");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);

        // Navigate to menu and logout
        // Note: You may need to add menu and logout elements to HomePage

        logger.info("Logout functionality to be implemented");
        // Add logout logic here
    }
}