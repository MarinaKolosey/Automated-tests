package UI.forms;

import org.openqa.selenium.By;

import UI.menu.NavigateMenu;
import webdriver.BaseForm;

/*
 * Main page of Onliner.by
 */
public class MainForm extends BaseForm {
   private static final String locator = "//div/a[@href='http://www.onliner.by/']";
   private static final String formTitle = "onliner";
   private NavigateMenu navigateMenu = new NavigateMenu();

   public MainForm() {
	 super(By.xpath(locator), formTitle);
   }
 
   /*
    * Gets Menu tab
    * @return tab's name of Menu
    */
   public NavigateMenu getMenu() {
     return navigateMenu;
   }
 
   public void assertIsOpen() {
	 super.assertIsOpen();
   }
}
