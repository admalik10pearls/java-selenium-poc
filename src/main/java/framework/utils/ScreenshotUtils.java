package framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class ScreenshotUtils {

    private static final Logger log = LoggerUtils.getLogger(ScreenshotUtils.class);

    public static byte[] captureScreenshotAsBytes(WebDriver driver) {
        log.info("Capturing screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
