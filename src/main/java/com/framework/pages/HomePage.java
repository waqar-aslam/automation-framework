package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(xpath = "//button[contains(@class, 'btn_inventory')]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get home page title")
    public String getPageTitle() {
        logger.info("Getting home page title");
        return getText(pageTitle);
    }

    @Step("Get inventory items count")
    public int getInventoryItemsCount() {
        logger.info("Getting inventory items count");
        return inventoryItems.size();
    }

    @Step("Add first item to cart")
    public HomePage addFirstItemToCart() {
        logger.info("Adding first item to cart");
        if (!addToCartButtons.isEmpty()) {
            clickElement(addToCartButtons.get(0));
        }
        return this;
    }

    @Step("Add item to cart by index: {index}")
    public HomePage addItemToCart(int index) {
        logger.info("Adding item at index {} to cart", index);
        if (index < addToCartButtons.size()) {
            clickElement(addToCartButtons.get(index));
        }
        return this;
    }

    @Step("Navigate to cart")
    public CartPage goToCart() {
        logger.info("Navigating to cart");
        clickElement(cartLink);
        return new CartPage(driver);
    }

    @Step("Sort items by: {sortOption}")
    public HomePage sortItems(String sortOption) {
        logger.info("Sorting items by: {}", sortOption);
        selectByVisibleText(sortDropdown, sortOption);
        return this;
    }

    @Step("Verify home page loaded successfully")
    public boolean isHomePageLoaded() {
        logger.info("Verifying home page loaded");
        return isElementDisplayed(pageTitle) && !inventoryItems.isEmpty();
    }

    @Step("Click menu button")
    public HomePage clickMenu() {
        logger.info("Clicking menu button");
        clickElement(menuButton);
        return this;
    }

    @Step("Click logout")
    public LoginPage clickLogout() {
        logger.info("Clicking logout");
        clickElement(logoutLink);
        return new LoginPage(driver);
    }

    @Step("Logout from application")
    public LoginPage logout() {
        logger.info("Logging out");
        clickMenu();
        // Wait for menu to open
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return clickLogout();
    }
}