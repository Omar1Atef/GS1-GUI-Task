package com.assessment.gui.pages;

import com.assessment.gui.config.Configuration;
import org.openqa.selenium.By;

/** Landing page of the-internet; the entry point for every GUI journey. */
public class HomePage extends BasePage {

    private static final By HEADING = By.cssSelector("h1.heading");

    private static By link(String linkText) {
        return By.linkText(linkText);
    }

    public HomePage open() {
        driver.get(Configuration.baseUrl());
        visible(HEADING);
        return this;
    }

    public FileUploadPage openFileUpload() {
        click(link("File Upload"));
        return new FileUploadPage();
    }

    public DynamicLoadingPage openDynamicLoading() {
        click(link("Dynamic Loading"));
        return new DynamicLoadingPage();
    }
}
