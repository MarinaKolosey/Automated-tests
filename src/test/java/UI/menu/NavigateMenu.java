package UI.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webdriver.BaseForm;

/*
 * Navigation by Onliner Menu
 */
public class NavigateMenu extends BaseForm {
    String menuTemplate = "//a[@href='http://%s.onliner.by/']";
 
    public NavigateMenu() {
	   super(By.xpath("//ul[@class='b-main-navigation']"), "Main Menu Onliner.by");
	   }
	
    /*
     * Opens required Menu's tab
     * @param menuLink Name of tab 
     */
	public void openTab(String menuLink) {
		WebElement navigatMenuElement = browser.getDriver().findElement(By.xpath(String.format(menuTemplate,menuLink)));
	    navigatMenuElement.click();		
	}
}
