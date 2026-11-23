package com.assessment.gui.pages;

import org.openqa.selenium.By;

/**
 * {@code /upload} result page - confirms which file the application received.
 *
 * <p>The upload form page also contains an {@code <h3>} ("File Uploader"), so waiting on the
 * heading alone would be satisfied by the previous page before the submit has navigated.
 * This page therefore waits for {@code #uploaded-files}, which exists only after an upload,
 * before any value can be read from it.
 */
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
