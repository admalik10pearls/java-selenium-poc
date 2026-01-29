package tests.ui;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.LoggerUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    void validUserCanLogin() {
        LoggerUtils.info("Starting login test");
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        loginPage.open();
        LoggerUtils.info("Login page opened");

        loginPage.loginWithDefaultUser();
        LoggerUtils.info("Login submitted");

        assertTrue(inventoryPage.isLoaded());
        LoggerUtils.info("Inventory page loaded successfully");
    }
}
