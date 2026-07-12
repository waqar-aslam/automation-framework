package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "cart_quantity")
    private List<WebElement> cartQuantities;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(className = "cart_desc_label")
    private WebElement cartDescription;

    @FindBy(xpath = "//button[text()='Remove']")
    private List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify cart page is loaded")
    public boolean isCartPageLoaded() {
        logger.info("Verifying cart page loaded");
        return isElementDisplayed(cartDescription) || !cartItems.isEmpty();
    }

    @Step("Get number of items in cart")
    public int getCartItemCount() {
        logger.info("Getting cart item count");
        return cartItems.size();
    }

    @Step("Get item name at index: {index}")
    public String getItemName(int index) {
        logger.info("Getting item name at index: {}", index);
        if (index < itemNames.size()) {
            return getText(itemNames.get(index));
        }
        return null;
    }

    @Step("Get item price at index: {index}")
    public String getItemPrice(int index) {
        logger.info("Getting item price at index: {}", index);
        if (index < itemPrices.size()) {
            return getText(itemPrices.get(index));
        }
        return null;
    }

    @Step("Remove item at index: {index}")
    public CartPage removeItem(int index) {
        logger.info("Removing item at index: {}", index);
        if (index < removeButtons.size()) {
            clickElement(removeButtons.get(index));
        }
        return this;
    }

    @Step("Proceed to checkout")
    public CheckoutPage clickCheckout() {
        logger.info("Clicking checkout button");
        clickElement(checkoutButton);
        return new CheckoutPage(driver);
    }

    @Step("Continue shopping")
    public HomePage clickContinueShopping() {
        logger.info("Clicking continue shopping button");
        clickElement(continueShoppingButton);
        return new HomePage(driver);
    }

    @Step("Get all item names in cart")
    public List<String> getAllItemNames() {
        logger.info("Getting all item names from cart");
        return itemNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Get all item prices in cart")
    public List<String> getAllItemPrices() {
        logger.info("Getting all item prices from cart");
        return itemPrices.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Verify item exists in cart: {itemName}")
    public boolean isItemInCart(String itemName) {
        logger.info("Verifying item exists in cart: {}", itemName);
        return getAllItemNames().stream()
                .anyMatch(name -> name.equalsIgnoreCase(itemName));
    }

    @Step("Calculate total items in cart")
    public int getTotalQuantity() {
        logger.info("Calculating total quantity in cart");
        return cartQuantities.stream()
                .mapToInt(element -> Integer.parseInt(element.getText()))
                .sum();
    }
}