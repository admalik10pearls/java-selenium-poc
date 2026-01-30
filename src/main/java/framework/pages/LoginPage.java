package framework.pages;

import framework.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    // Locators
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(ConfigReader.get("base.url"));
        wait.waitForVisible(usernameInput);
    }

    public void login(String username, String password) {
        wait.waitForVisible(usernameInput);
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        wait.waitForClickable(loginButton);
        driver.findElement(loginButton).click();
    }

    /**
     * Uses credentials from config.properties
     */
    public void loginWithDefaultUser() {
        login(
                ConfigReader.get("app_username"),
                ConfigReader.get("app_password")
        );
    }
}
