package com.assessment.gui.base;

import com.assessment.gui.driver.DriverManager;
import com.assessment.gui.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Starts a fresh browser session per test method and always releases it,
 * so no test has to manage setup or teardown itself.
 */
public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void startBrowser() {
        DriverManager.startSession();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        DriverManager.quitSession();
    }

    /** Fluent entry point: every test starts from the opened home page. */
    protected HomePage homePage() {
        return new HomePage().open();
    }
}
