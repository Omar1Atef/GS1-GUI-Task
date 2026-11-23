package com.assessment.gui.driver;

import java.util.Arrays;
import java.util.stream.Collectors;

/** Browsers supported by {@link DriverFactory}. */
public enum BrowserType {

    CHROME,
    FIREFOX;

    public static BrowserType from(String value) {
        return Arrays.stream(values())
                .filter(browser -> browser.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported browser '" + value + "'. Supported: "
                                + Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", "))));
    }
}
