package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(className = "pony_express")
    private WebElement ponyExpressImage;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify checkout complete page is loaded")
    public boolean isCompletePageLoaded() {
        logger.info("Verifying checkout complete page loaded");
        return isElementDisplayed(completeHeader) &&
                isElementDisplayed(ponyExpressImage);
    }

    @Step("Get completion header message")
    public String getCompletionHeader() {
        logger.info("Getting completion header");
        return getText(completeHeader);
    }

    @Step("Get completion text message")
    public String getCompletionText() {
        logger.info("Getting completion text");
        return getText(completeText);
    }

    @Step("Click back to products button")
    public HomePage clickBackToProducts() {
        logger.info("Clicking back to products button");
        clickElement(backToProductsButton);
        return new HomePage(driver);
    }

    @Step("Verify order placed successfully")
    public boolean isOrderSuccessful() {
        logger.info("Verifying order successful");
        String header = getCompletionHeader();
        return header.contains("Thank you") || header.contains("Complete");
    }
}