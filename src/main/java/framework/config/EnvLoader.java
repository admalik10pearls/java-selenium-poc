package framework.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    private static final Dotenv DOTENV = Dotenv.configure()
            .ignoreIfMissing()
            .load(); // Loads .env from project root

    /**
     * Attempts to get a value from:
     * 1. OS environment
     * 2. .env file
     * Returns null if not found in either.
     */
    public static String get(String key) {
        // Check OS environment variables first
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        // Then check .env file
        return DOTENV.get(key);
    }
}
