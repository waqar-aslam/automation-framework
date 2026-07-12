package com.framework.base;

import com.framework.config.ConfigReader;
import com.framework.utils.BrowserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseClass {

    public static WebDriver driver;

    // Make logger public static so it can be accessed anywhere
    public static Logger logger = LogManager.getLogger(BaseClass.class);

    protected ConfigReader configReader;

    @BeforeSuite
    public void setUpSuite() {
        logger.info("========== SUITE EXECUTION STARTED ==========");
        configReader = new ConfigReader();
        logger.info("ConfigReader initialized successfully");
    }

    @BeforeTest
    @Parameters("browser")
    public void setUpTest(@Optional("chrome") String browser) {
        logger.info("Initializing WebDriver for browser: {}", browser);

        if (configReader == null) {
            logger.warn("ConfigReader is null, initializing now...");
            configReader = new ConfigReader();
        }

        String url = configReader.getProperty("url");
        logger.info("URL from config: {}", url);

        driver = BrowserFactory.initializeDriver(browser);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        logger.info("Navigated to URL: {}", url);
    }

    @AfterTest
    public void tearDownTest() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.quit();
            driver = null;
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        logger.info("========== SUITE EXECUTION COMPLETED ==========");
    }

    public static WebDriver getDriver() {
        return driver;
    }
}