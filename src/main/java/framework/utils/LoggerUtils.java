package framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    // Private constructor to prevent instantiation
    private LoggerUtils() {}

    /**
     * Returns a logger for the calling class.
     *
     * Usage:
     * private static final Logger log = LoggerUtils.getLogger(MyClass.class);
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * Convenience methods for logging
     */
    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void debug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    public static void warn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    public static void error(Class<?> clazz, String message, Throwable t) {
        getLogger(clazz).error(message, t);
    }
}
