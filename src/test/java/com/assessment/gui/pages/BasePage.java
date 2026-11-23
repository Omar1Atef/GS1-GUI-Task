package com.assessment.gui.pages;

import com.assessment.gui.config.Configuration;
import com.assessment.gui.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;

/**
 * Shared page behaviour: every interaction is synchronised through an explicit
 * wait, so no page object needs its own waiting logic and no test needs a sleep.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.driver();
        this.wait = new WebDriverWait(driver, Configuration.explicitWait());
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String textOf(By locator) {
        return visible(locator).getText().trim();
    }

    protected void waitUntilInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void attachFile(By fileInput, Path file) {
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput))
                .sendKeys(file.toAbsolutePath().toString());
    }
}
