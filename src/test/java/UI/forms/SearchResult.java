package UI.forms;

import org.openqa.selenium.By;
import org.testng.Assert;

import webdriver.BaseForm;

/*
 * The page of the search result
 */
public class SearchResult extends BaseForm {
	private static final String locator = "//ul[@class ='b-offers-subnav']";
	private static final String formTitle = "Search Result";
	
	public SearchResult() {
		 super(By.xpath(locator), formTitle);
	 }
	
	/*
	 * Checks if the manufacturer of found TV matches to the requested manufacturer
	 * @param itemLink Manufacturer
	 */
	public boolean checkManufacturerMatch (String itemLink) {
		boolean manufactureMatch = browser.getDriver().findElement(By.xpath("//h2[@class='catalog-masthead__title']")).getText().contains(itemLink);
	    logger.info(String.format("Manufacture should be '%s', result '%s'", itemLink, String.valueOf(manufactureMatch)));
	    return manufactureMatch;
	}
	
	/*
	 * Checks if the release year of found TV matches to the requested year range
	 * @param minYear Minimal year
	 */
	public void checkYearMatch (String minYear) {
		String yearMatch = browser.getDriver().findElement(By.xpath("//tr[contains(.,'Дата выхода на рынок')]/td/span")).getText().substring(0, 4);
	    int yearMatchInt = Integer.parseInt(yearMatch);
	    int minYearInt = Integer.parseInt(minYear);
		info(String.format("Year should not be less '%d', found '%d'", minYearInt, yearMatchInt));
        Assert.assertTrue(yearMatchInt >= minYearInt);
    }
	
	/*
	 * Checks if the diagonal's value of found TV matches to the requested diagonal range
	 * @param minDiagonal Minimal diagonal
     * @param maxDiagonal Maximal diagonal
	 */
	public void checkDiagonalMatch (int minDiagonal, int maxDiagonal) {
		String diagonalMatch = browser.getDriver().findElement(By.xpath("//tr[contains(.,'Диагональ экрана')]/td/span")).getText().replace("\"", "");
	    int diagonalMatchInt = Integer.parseInt(diagonalMatch);
	    info(String.format("Diagonal should be from '%d' to '%d', found '%d'", minDiagonal, maxDiagonal, diagonalMatchInt));
        Assert.assertTrue(diagonalMatchInt >= minDiagonal && diagonalMatchInt <=maxDiagonal);
    }
	
	/*
	 * Checks if the price of found TV matches to the requested price range
	 * @param maxPrice Maximal price
	 */
	public void priceMatch (String maxPrice) {
		String priceMatch = browser.getDriver().findElement(By.xpath("//div[@class='b-offers-desc']//div[@class='b-offers-desc__info-sub']/a")).getText();
		if (priceMatch.contains("–")) {
			priceMatch = priceMatch.split("–")[0];
		}
		priceMatch = priceMatch.replaceAll("[^0-9]", "");
		int priceMatchInt = Integer.parseInt(priceMatch);
		int maxPriceInt = Integer.parseInt(maxPrice);
	    info(String.format("Price should be less '%d', found '%d'", maxPriceInt, priceMatchInt));
        Assert.assertTrue(priceMatchInt <= maxPriceInt);
	}
	
     /*
      * Navigates from the Search Result page back to the TVs page
      */
     public void goBack() {
	     browser.getDriver().navigate().back();
     }
}
