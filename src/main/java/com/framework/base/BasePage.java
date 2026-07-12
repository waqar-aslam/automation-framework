package com.framework.base;

import com.framework.utils.WebDriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverHelper webDriverHelper;
    protected Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.webDriverHelper = new WebDriverHelper(driver);
        PageFactory.initElements(driver, this);
        logger.info("BasePage initialized");
    }

    // ============ ALL THESE METHODS MUST EXIST ============

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickElement(WebElement element) {
        webDriverHelper.clickElement(element);
    }

    public void enterText(WebElement element, String text) {
        webDriverHelper.sendKeys(element, text);
    }

    public boolean isElementDisplayed(WebElement element) {
        return webDriverHelper.isElementDisplayed(element);
    }

    // ======== THIS IS THE METHOD THAT WAS MISSING ========
    public String getText(WebElement element) {
        return webDriverHelper.getText(element);
    }
    // =====================================================

    public void selectByVisibleText(WebElement element, String text) {
        webDriverHelper.selectByVisibleText(element, text);
    }

    public void selectByValue(WebElement element, String value) {
        webDriverHelper.selectByValue(element, value);
    }

    public void selectByIndex(WebElement element, int index) {
        webDriverHelper.selectByIndex(element, index);
    }
}