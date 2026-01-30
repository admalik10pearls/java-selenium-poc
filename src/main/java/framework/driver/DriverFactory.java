package framework.driver;

import framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // Headless toggle from config
            if (ConfigReader.isHeadless()) {
                options.addArguments("--headless=new");         // headless mode
                options.addArguments("--disable-gpu");           // improves compatibility
                options.addArguments("--no-sandbox");            // useful in CI
                options.addArguments("--disable-dev-shm-usage"); // reduces memory issues
                options.addArguments("--window-size=1920,1080"); // prevent tiny window issues
            }

            return new ChromeDriver(options);
        }

        // add other browser support (Firefox, WebKit) if needed
        throw new IllegalArgumentException("Browser not supported: " + browser);
    }
}
