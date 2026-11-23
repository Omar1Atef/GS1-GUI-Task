package com.assessment.gui.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicLoadingTestData {

    private String caseName;
    private int exampleNumber;
    private String expectedText;

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public int getExampleNumber() {
        return exampleNumber;
    }

    public void setExampleNumber(int exampleNumber) {
        this.exampleNumber = exampleNumber;
    }

    public String getExpectedText() {
        return expectedText;
    }

    public void setExpectedText(String expectedText) {
        this.expectedText = expectedText;
    }

    @Override
    public String toString() {
        return caseName;
    }
}
