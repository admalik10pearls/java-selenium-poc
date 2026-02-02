package framework.tests.ui;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.LoggerUtils;
import framework.utils.TestDataReader;
import org.slf4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private static final Logger log = LoggerUtils.getLogger(LoginTest.class);

    @Test(description = "Login via valid credentials")
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
    @Test(description = "Login via valid credentials using test Data")
    void loginWithValidUser() {
        TestDataReader testData = new TestDataReader("testData.json");

        String username = testData.getString("validUser.username");
        String password = testData.getString("validUser.password");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        assertEquals(username, "standard_user");
    }
}