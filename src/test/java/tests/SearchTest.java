package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import UI.forms.CatalogForm;
import UI.forms.MainForm;
import UI.forms.TVform;
import webdriver.BaseTest;

/*
 * Class describes all steps of the Test
 */
public class SearchTest extends BaseTest {
	private String menuLink;
	private String itemLink;
	private String maxPrice;
	private String minYear;
	private int minDiagonal;
	private int maxDiagonal;
	
	@Test
	@Parameters({ "menuLink","itemLink", "maxPrice", "minYear", "minDiagonal", "maxDiagonal" })
	public void readParams(String menuLink, String itemLink, String maxPrice, String minYear, int minDiagonal, int maxDiagonal) throws Throwable{
		this.menuLink = menuLink;
		this.itemLink = itemLink;
		this.maxPrice = maxPrice;
		this.minYear = minYear;
		this.minDiagonal = minDiagonal;
		this.maxDiagonal = maxDiagonal;
		xTest();
	}

	@Test(enabled = false)
	public void xTest() throws Throwable {
		super.xTest();
	}
	
	public void runTest() {
		logger.step(1);
		MainForm mainForm = new MainForm();
				
		logger.step(2);
		mainForm.getMenu().openTab(menuLink);
		CatalogForm catalogForm = new CatalogForm();
				
		logger.step(3);
		catalogForm.openSubmenu();
		TVform tvform = new TVform();
	    
	    logger.step(4,5);
	    tvform.fillModelCriteria (itemLink);
	    tvform.fillPriceCriteria(maxPrice);
	    tvform.fillYearCriteria(minYear);
	    tvform.fillDiagonalValue(minDiagonal,maxDiagonal);
	
	    logger.step(6);
	    tvform.checkParametrs(itemLink, maxPrice, minYear, minDiagonal, maxDiagonal);
	    
	}
}
