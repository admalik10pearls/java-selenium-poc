package framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {

    private static final Logger log = LoggerUtils.getLogger(ScreenshotUtils.class);
    public static void captureScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("screenshots"));
            String dest = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            Files.copy(srcFile.toPath(), Paths.get(dest));
            log.info("Screenshot saved: {}", dest);
        } catch (IOException e) {
            log.error("Failed to save screenshot: {}", e.getMessage());
        }
    }
}
