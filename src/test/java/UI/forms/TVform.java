package UI.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;
import webdriver.elements.TextBox;

/*
 * The page "Телевизоры"
 */
public class TVform extends BaseForm {
	private static final String locator = "//h1[text() ='Телевизоры']";
	private static final String formTitle = "Телевизоры";
	private TextBox maxPriceBox = new TextBox(By.xpath("//input[@placeholder ='до']"), "Max Price");
	String itemLink;
	private CheckBox checkBoxCriteria = new CheckBox (By.xpath("//div/span[text() = 'Производитель']"),(String.format ("Производитель: %s ", itemLink)));
	private TextBox minYearValue = new TextBox (By.xpath("//input[@placeholder='2011']"),"Min Year");	
	WebElement minDiagonal = browser.getDriver().findElement(By.xpath("//select[contains(@data-bind,\"from\")]"));
	Select minValue = new Select(minDiagonal);
	WebElement maxDiagonal = browser.getDriver().findElement(By.xpath("//select[contains(@data-bind,\"to\")]"));
	Select maxValue = new Select(maxDiagonal);
	   	
	public TVform () {
		super(By.xpath(locator), formTitle);
	}
	
	public void assertIsOpen() {
		super.assertIsOpen();
    }
	
	/*
	 * Selects Manufacturer of TVs
	 * @param itemLink Manufacturer
	 */
	public void fillModelCriteria (String itemLink) {
		checkBoxCriteria.checkInput (itemLink);
		checkBoxCriteria.checkItem(itemLink);
	}
	
	/*
	 * Inputs maximal price of TVs
	 * @param maxPrice Maximal price
	 */
	public void fillPriceCriteria(String maxPrice) {
		maxPriceBox.setText(maxPrice);
	}
	
	/*
	 * Inputs minimal year
	 * @param minYear Minimal year
	 */
    public void fillYearCriteria (String minYear) {
	    minYearValue.setText (minYear);
    }

    /*
     * Inputs diagonal's range of TVs
     * @param minDiagonal Minimal diagonal
     * @param maxDiagonal Maximal diagonal
     */
    public void fillDiagonalValue (int minDiagonal,int maxDiagonal) {
	     minValue.selectByVisibleText(Integer.toString(minDiagonal)+"\"");
	     info(String.format("Drop-down Min Diagonal: Checking '%d'", minDiagonal));
	     maxValue.selectByVisibleText(Integer.toString(maxDiagonal)+"\"");
	     info(String.format("Drop-down Max Diagonal: Checking '%d'", maxDiagonal));
	     //browser.waitForJQuery();
    }
    
   /*
    * Gets the count of the search results
    * @return count of the search results    
    */
    public int getResultCount() {
    	return browser.getDriver().findElements(By.xpath("//span[@data-bind='html: product.full_name']")).size();
	    /*int i = browser.getDriver().findElements(By.xpath("//span[@data-bind='html: product.full_name']")).size();
	    System.out.println(i);
	    return i;*/
    }

    /*
     * Opens the search result page
     * @param i Index number of the search result
     */
    public void clickOnResult (int i) {
	   WebElement searchResult = browser.getDriver().findElement(By.xpath(String.format("(//span[@data-bind='html: product.full_name'])[%d]",i)));
	   searchResult.click();
	 }
    
    /*
     * Check if all found search results match to the search parameters
     * @param itemLink Manufacturer
     * @param maxPrice Maximal price
     * @param minYear Minimal year
     * @param minDiagonal Minimal diagonal
     * @param maxDiagonal Maximal diagonal
     */
    public void checkParametrs (String itemLink, String maxPrice, String minYear, int minDiagonal, int maxDiagonal) {
	   int count = getResultCount();
	   
	   for (int i=1;i<=count;i++) {
	   clickOnResult(i);  
	   SearchResult searchResult = new SearchResult();
	   searchResult.checkManufacturerMatch(itemLink);
	   searchResult.checkYearMatch(minYear);
	   searchResult.priceMatch(maxPrice);
	   searchResult.checkDiagonalMatch(minDiagonal,maxDiagonal);
	   searchResult.goBack();
	   }
	}
}
