package com.assessment.gui.pages;

import org.openqa.selenium.By;


public class DynamicLoadingExamplePage extends BasePage {

    private static final By START_BUTTON = By.cssSelector("#start button");
    private static final By LOADING_INDICATOR = By.id("loading");
    private static final By FINISH_TEXT = By.id("finish");

    public DynamicLoadingExamplePage start() {
        click(START_BUTTON);
        return this;
    }

    public DynamicLoadingExamplePage waitUntilLoadingFinished() {
        waitUntilInvisible(LOADING_INDICATOR);
        return this;
    }

    /** Text revealed once loading completes. */
    public String loadedText() {
        return textOf(FINISH_TEXT);
    }
}
