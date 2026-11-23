package com.assessment.gui.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadTestData {

    private String caseName;
    private String file;
    private String expectedHeading;

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getExpectedHeading() {
        return expectedHeading;
    }

    public void setExpectedHeading(String expectedHeading) {
        this.expectedHeading = expectedHeading;
    }

    /** File name only, which is what the application echoes back after upload. */
    public String expectedFileName() {
        return file.substring(file.lastIndexOf('/') + 1);
    }

    @Override
    public String toString() {
        return caseName;
    }
}
