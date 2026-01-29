package framework.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtils {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        System.out.println("[INFO] " + dtf.format(LocalDateTime.now()) + " - " + message);
    }

    public static void error(String message) {
        System.err.println("[ERROR] " + dtf.format(LocalDateTime.now()) + " - " + message);
    }
}
