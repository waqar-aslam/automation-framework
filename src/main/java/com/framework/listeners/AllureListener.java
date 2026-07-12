package com.framework.listeners;

import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.openqa.selenium.WebDriver;
import com.framework.base.BaseClass;
import java.io.ByteArrayInputStream;

public class AllureListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseClass) currentClass).getDriver();  // Use getDriver() method

        if (driver != null) {
            try {
                byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver)
                        .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);

                // Fix: Convert byte[] to InputStream using ByteArrayInputStream
                Allure.addAttachment(
                        "Failed Screenshot",                    // Name
                        "image/png",                            // Content type
                        new ByteArrayInputStream(screenshot),   // InputStream (not byte[])
                        ".png"                                  // File extension
                );
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }
}