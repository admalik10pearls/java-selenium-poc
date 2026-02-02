package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
import framework.utils.LoggerUtils;
import framework.utils.ScreenshotUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public abstract class BaseTest {
    private static final Logger log = LoggerUtils.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        String browser = System.getProperty(
                "browser",
                ConfigReader.get("browser")
        );

        driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        log.info("Running tests on browser: {}", browser);

    }

    @AfterEach
    void tearDown(org.junit.jupiter.api.TestInfo testInfo) {
        if (testInfo.getTags().contains("FAILED")) {
            ScreenshotUtils.captureScreenshot(driver, testInfo.getDisplayName());
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
