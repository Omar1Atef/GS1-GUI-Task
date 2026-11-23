package com.assessment.gui.utils;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Resolves a file on the test classpath to a real filesystem path. */
public final class TestResources {

    private TestResources() {
    }

    public static Path path(String classpathLocation) {
        URL resource = TestResources.class.getClassLoader().getResource(classpathLocation);
        if (resource == null) {
            throw new IllegalArgumentException("Test resource not found on classpath: " + classpathLocation);
        }
        try {
            return Paths.get(resource.toURI());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to resolve test resource: " + classpathLocation, e);
        }
    }
}
