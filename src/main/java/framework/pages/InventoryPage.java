package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = By.id("inventory_container");
    private final By inventoryItems = By.className("inventory_item");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public int getInventoryCount() {
        return driver.findElements(inventoryItems).size();
    }

    public boolean isLoaded() {
        wait.waitForVisible(inventoryContainer);
        return driver.findElement(inventoryContainer).isDisplayed();
    }
}
