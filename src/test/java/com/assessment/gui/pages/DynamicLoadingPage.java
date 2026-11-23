package com.assessment.gui.pages;

import org.openqa.selenium.By;

/** {@code /dynamic_loading} - index of the dynamic loading examples. */
public class DynamicLoadingPage extends BasePage {

    private static By example(int number) {
        return By.partialLinkText("Example " + number);
    }

    public DynamicLoadingExamplePage openExample(int number) {
        click(example(number));
        return new DynamicLoadingExamplePage();
    }
}
