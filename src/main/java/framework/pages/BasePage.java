package framework.pages;

import framework.config.ConfigReader;
import framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitUtils wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        long timeout = Long.parseLong(ConfigReader.get("explicit.wait"));
        this.wait = new WaitUtils(driver, timeout);
    }
}
