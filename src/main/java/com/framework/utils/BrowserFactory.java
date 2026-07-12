package com.framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BrowserFactory {

    // Add logger here
    private static final Logger logger = LogManager.getLogger(BrowserFactory.class);

    public static WebDriver initializeDriver(String browser) {
        WebDriver driver;
        logger.info("Initializing browser: {}", browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                logger.info("Setting up ChromeDriver...");
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                logger.info("ChromeDriver initialized successfully");
                break;

            case "firefox":
                try {
                    logger.info("Setting up FirefoxDriver...");
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--disable-notifications");
                    driver = new FirefoxDriver(firefoxOptions);
                    logger.info("FirefoxDriver initialized successfully");
                } catch (Exception e) {
                    logger.error("Firefox not found, falling back to Chrome");
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions fallbackOptions = new ChromeOptions();
                    fallbackOptions.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(fallbackOptions);
                    logger.info("Falling back to ChromeDriver");
                }
                break;

            case "edge":
                try {
                    logger.info("Setting up EdgeDriver...");
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new EdgeDriver(edgeOptions);
                    logger.info("EdgeDriver initialized successfully");
                } catch (Exception e) {
                    logger.error("Edge not found, falling back to Chrome");
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions fallbackOptions = new ChromeOptions();
                    fallbackOptions.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(fallbackOptions);
                    logger.info("Falling back to ChromeDriver");
                }
                break;

            case "headless":
                logger.info("Setting up Headless ChromeDriver...");
                WebDriverManager.chromedriver().setup();
                ChromeOptions headlessOptions = new ChromeOptions();
                headlessOptions.addArguments("--headless");
                headlessOptions.addArguments("--disable-gpu");
                headlessOptions.addArguments("--window-size=1920,1080");
                headlessOptions.addArguments("--remote-allow-origins=*");
                headlessOptions.addArguments("--no-sandbox");
                headlessOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(headlessOptions);
                logger.info("Headless ChromeDriver initialized successfully");
                break;

            default:
                logger.info("Browser not recognized: {}, defaulting to Chrome", browser);
                WebDriverManager.chromedriver().setup();
                ChromeOptions defaultOptions = new ChromeOptions();
                defaultOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(defaultOptions);
                break;
        }

        return driver;
    }
}