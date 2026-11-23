package com.assessment.gui.driver;

import com.assessment.gui.config.Configuration;
import org.openqa.selenium.WebDriver;


public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void startSession() {
        DRIVER.set(DriverFactory.create(
                BrowserType.from(Configuration.browser()),
                Configuration.headless()));
    }

    public static WebDriver driver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("No browser session for this thread. Was startSession() called?");
        }
        return driver;
    }

    public static void quitSession() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                DRIVER.remove();
            }
        }
    }
}
