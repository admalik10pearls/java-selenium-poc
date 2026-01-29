package framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static void captureScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("screenshots"));
            String dest = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            Files.copy(srcFile.toPath(), Paths.get(dest));
            LoggerUtils.info("Screenshot saved: " + dest);
        } catch (IOException e) {
            LoggerUtils.error("Failed to save screenshot: " + e.getMessage());
        }
    }
}
