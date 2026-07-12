package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(className = "summary_info")
    private WebElement summaryInfo;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify checkout overview page is loaded")
    public boolean isOverviewPageLoaded() {
        logger.info("Verifying checkout overview page loaded");
        return isElementDisplayed(summaryInfo);
    }

    @Step("Get subtotal amount")
    public String getSubtotal() {
        logger.info("Getting subtotal");
        return getText(subtotalLabel);
    }

    @Step("Get tax amount")
    public String getTax() {
        logger.info("Getting tax");
        return getText(taxLabel);
    }

    @Step("Get total amount")
    public String getTotal() {
        logger.info("Getting total");
        return getText(totalLabel);
    }

    @Step("Click finish button")
    public CheckoutCompletePage clickFinish() {
        logger.info("Clicking finish button");
        clickElement(finishButton);
        return new CheckoutCompletePage(driver);
    }

    @Step("Click cancel button")
    public CartPage clickCancel() {
        logger.info("Clicking cancel button");
        clickElement(cancelButton);
        return new CartPage(driver);
    }

    @Step("Get number of items in overview")
    public int getItemCount() {
        logger.info("Getting item count in overview");
        return cartItems.size();
    }
}