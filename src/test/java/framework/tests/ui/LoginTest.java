package framework.tests.ui;

import framework.base.BaseTest;
import framework.driver.DriverManager;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.LoggerUtils;
import framework.utils.TestDataReader;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.slf4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private static final Logger log = LoggerUtils.getLogger(LoginTest.class);

    @Test(description = "Login via valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    void validUserCanLogin() {
        log.info("Starting login test");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());

        loginPage.waitForPageLoad();
        loginPage.loginWithDefaultUser();

        assertTrue(inventoryPage.isLoaded());
        log.info("Inventory page loaded successfully");
    }

    @Test(description = "Login via valid credentials using test Data")
    @Severity(SeverityLevel.CRITICAL)
    void loginWithValidUser() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());
        TestDataReader testData = new TestDataReader("testData.json");
        String username = testData.getString("validUser.username");
        String pass = testData.getString("validUser.password");
        loginPage.waitForPageLoad();
        loginPage.login(username , pass );
        assertTrue(inventoryPage.isLoaded());
        log.info("Inventory page loaded successfully via test data");
    }
}