package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * First tries OS env var, then .env, then config.properties.
     */
    public static String get(String key) {
        // Priority 1: OS or .env via EnvLoader
        String value = EnvLoader.get(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Priority 2: config.properties
        return properties.getProperty(key);
    }
    public static boolean isHeadless() {
        String val = get("headless");
        // default if null/empty -> false
        return val != null && val.equalsIgnoreCase("true");
    }
}
