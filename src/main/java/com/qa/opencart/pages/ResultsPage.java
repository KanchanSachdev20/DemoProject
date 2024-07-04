package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opecart.utils.ElementUtils;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtils ele;
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtils(driver);
	}
	
	private By resultProduct = By.cssSelector("div.product-layout.product-grid");
	
	public String getResultsPageTitle(String searchKey) {
		return ele.waitForTitleIsAndCapture(searchKey, 5);
	}
	
	public int getProductResultCount() {
		int resultCount = ele.waitForElementsVisible(resultProduct, 10).size();
		System.out.println("Product count is"+ resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productNameLocator = By.linkText(productName);
		ele.doClick(productNameLocator);
		return new ProductInfoPage(driver);
	}
}
