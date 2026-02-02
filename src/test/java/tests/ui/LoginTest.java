package tests.ui;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.LoggerUtils;
import framework.utils.ScreenshotUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    private static final Logger log = LoggerUtils.getLogger(LoginTest.class);

    @Test
    void validUserCanLogin() {
        log.info("Starting login test");
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        loginPage.open();
        log.info("Login page opened");

        loginPage.loginWithDefaultUser();
        log.info("Login submitted");

        assertTrue(inventoryPage.isLoaded());
        log.info("Inventory page loaded successfully");
    }
}
