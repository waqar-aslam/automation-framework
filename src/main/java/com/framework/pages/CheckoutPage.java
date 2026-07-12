package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='checkout_info']")
    private WebElement checkoutInfo;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify checkout page is loaded")
    public boolean isCheckoutPageLoaded() {
        logger.info("Verifying checkout page loaded");
        return isElementDisplayed(pageTitle) &&
                pageTitle.getText().contains("Checkout") &&
                isElementDisplayed(firstNameField);
    }

    @Step("Enter first name: {firstName}")
    public CheckoutPage enterFirstName(String firstName) {
        logger.info("Entering first name: {}", firstName);
        enterText(firstNameField, firstName);
        return this;
    }

    @Step("Enter last name: {lastName}")
    public CheckoutPage enterLastName(String lastName) {
        logger.info("Entering last name: {}", lastName);
        enterText(lastNameField, lastName);
        return this;
    }

    @Step("Enter postal code: {postalCode}")
    public CheckoutPage enterPostalCode(String postalCode) {
        logger.info("Entering postal code: {}", postalCode);
        enterText(postalCodeField, postalCode);
        return this;
    }

    @Step("Fill checkout information: {firstName}, {lastName}, {postalCode}")
    public CheckoutOverviewPage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        return clickContinue();
    }

    @Step("Click continue button")
    public CheckoutOverviewPage clickContinue() {
        logger.info("Clicking continue button");
        clickElement(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    @Step("Click cancel button")
    public CartPage clickCancel() {
        logger.info("Clicking cancel button");
        clickElement(cancelButton);
        return new CartPage(driver);
    }
}