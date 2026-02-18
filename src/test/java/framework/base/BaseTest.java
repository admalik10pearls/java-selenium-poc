package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
import framework.driver.DriverManager;
import framework.utils.LoggerUtils;
import framework.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

public abstract class BaseTest {

    private static final Logger log = LoggerUtils.getLogger(BaseTest.class);
    private String currentTestDescription;

    @BeforeMethod
    public void setUp(Method method) {
        Test testAnnotation = method.getAnnotation(Test.class);
        currentTestDescription = (testAnnotation != null && !testAnnotation.description().isEmpty())
                ? testAnnotation.description() : "No description provided";

        log.info("****STARTING TEST****: {}", currentTestDescription);

        String browser = System.getProperty("browser", ConfigReader.get("browser"));

        WebDriver driver = DriverFactory.createDriver(browser);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(ConfigReader.get("base.url"));
        log.info("Running tests on thread: {} | Browser: {}", Thread.currentThread().getId(), browser);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        log.info("****ENDING TEST****: {}", currentTestDescription);
        WebDriver driver = DriverManager.getDriver(); // CHANGE: Fetch thread-specific driver

        if (!result.isSuccess() && driver != null) {
            byte[] screenshot = ScreenshotUtils.captureScreenshotAsBytes(driver);
            Allure.addAttachment("Screenshot on failure", new ByteArrayInputStream(screenshot));
        }

        if (driver != null) {
            driver.quit();
        }
        DriverManager.unload();
    }
}