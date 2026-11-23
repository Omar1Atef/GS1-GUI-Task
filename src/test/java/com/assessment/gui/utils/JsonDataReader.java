package com.assessment.gui.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

/** Reads externalised JSON test data into the {@code Object[][]} TestNG expects. */
public final class JsonDataReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonDataReader() {
    }

    public static <T> Object[][] asDataProvider(String classpathLocation, Class<T> type) {
        return read(classpathLocation, type).stream()
                .map(item -> new Object[]{item})
                .toArray(Object[][]::new);
    }

    private static <T> List<T> read(String classpathLocation, Class<T> type) {
        try (InputStream stream = JsonDataReader.class.getClassLoader().getResourceAsStream(classpathLocation)) {
            if (stream == null) {
                throw new IllegalArgumentException("Test data file not found on classpath: " + classpathLocation);
            }
            return MAPPER.readValue(
                    stream,
                    MAPPER.getTypeFactory().constructCollectionType(List.class, type));
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read test data from " + classpathLocation, e);
        }
    }
}
