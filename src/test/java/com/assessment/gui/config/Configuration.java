package com.assessment.gui.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/**
 * Single source of truth for environment configuration.
 * Values come from {@code config.properties} and can be overridden at runtime
 * with system properties, e.g. {@code -Dbrowser=firefox -Dheadless=false}.
 */
public final class Configuration {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = load();

    private Configuration() {
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream stream = Configuration.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (stream == null) {
                throw new IllegalStateException(CONFIG_FILE + " was not found on the test classpath");
            }
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read " + CONFIG_FILE, e);
        }
    }

    private static String get(String key) {
        String value = System.getProperty(key, PROPERTIES.getProperty(key));
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing configuration value for key: " + key);
        }
        return value.trim();
    }

    public static String baseUrl() {
        return get("base.url");
    }

    public static String browser() {
        return get("browser");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static Duration explicitWait() {
        return Duration.ofSeconds(Long.parseLong(get("timeout.explicit.seconds")));
    }

    public static Duration pageLoadTimeout() {
        return Duration.ofSeconds(Long.parseLong(get("timeout.pageload.seconds")));
    }
}
