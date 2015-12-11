package UI.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webdriver.BaseForm;

/*
 * Catalog page 
 */
public class CatalogForm extends BaseForm {
	private static final String locator = "//div[@class = 'catalog-bar-main']";
	private static final String formTitle ="Catalog Menu";
	WebElement subMenu;
	 
    public CatalogForm () {
	   super(By.xpath(locator), formTitle);
    }
 
    public void assertIsOpen() {
	   super.assertIsOpen();
    }
 
    /*
     * Opens the TVs tab from the Catalog page
     */
    public void openSubmenu() {
	   subMenu = browser.getDriver().findElement(By.xpath("//li/a[text()='Телевизоры']"));
	   subMenu.click();
    }
}
