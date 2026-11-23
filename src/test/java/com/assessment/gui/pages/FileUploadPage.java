package com.assessment.gui.pages;

import org.openqa.selenium.By;

import java.nio.file.Path;

public class FileUploadPage extends BasePage {

    private static final By FILE_INPUT = By.id("file-upload");
    private static final By SUBMIT_BUTTON = By.id("file-submit");

    public FileUploadPage chooseFile(Path file) {
        attachFile(FILE_INPUT, file);
        return this;
    }

    public FileUploadedPage submit() {
        click(SUBMIT_BUTTON);
        return new FileUploadedPage();
    }
}
