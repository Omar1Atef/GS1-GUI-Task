package com.assessment.gui.pages;

import org.openqa.selenium.By;


public class FileUploadedPage extends BasePage {

    private static final By HEADING = By.tagName("h3");
    private static final By UPLOADED_FILE_NAME = By.id("uploaded-files");

    FileUploadedPage() {
        visible(UPLOADED_FILE_NAME);
    }

    public String heading() {
        return textOf(HEADING);
    }

    public String uploadedFileName() {
        return textOf(UPLOADED_FILE_NAME);
    }
}
