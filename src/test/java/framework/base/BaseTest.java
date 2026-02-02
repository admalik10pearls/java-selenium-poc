package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
import framework.utils.LoggerUtils;
import framework.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private static final Logger log = LoggerUtils.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty(
                "browser",
                ConfigReader.get("browser")
        );

        driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        log.info("Running tests on browser: {}", browser);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot if test failed
        if (!result.isSuccess() && driver != null) {
            String testName = result.getMethod().getMethodName();
            ScreenshotUtils.captureScreenshot(driver, testName);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
