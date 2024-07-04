package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opecart.utils.AppConstants;
import com.qa.opecart.utils.ElementUtils;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtils ele;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	
	private Map<String, String> productInfoMap;
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtils(driver);
	}

	public String getProductHeaderName() {
		return ele.doGetElementText(productHeader);
	}
	
	public int getProductImagesCount() {
		return ele.waitForElementsVisible(productImages, AppConstants.MEDIUM_DEFAULT_VALUE).size();
	}
	
	public Map<String, String> getProductInfo() {
		getProductMetaData();
		getProductPriceData();
		productInfoMap.put("productname", getProductHeaderName());
		return productInfoMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = ele.getElements(productMetaData);
		//productInfoMap = new HashMap<String,String>();
		productInfoMap = new LinkedHashMap<String,String>();
		for(WebElement e: metaList) {
			String metaText = e.getText();
			String metaInfo[] = metaText.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = ele.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTaxprice = priceList.get(1).getText();
		String exTaxpriceValue = exTaxprice.split(":")[1].trim();
			productInfoMap.put("productPrice", price);
			productInfoMap.put("exTaxPrice", exTaxpriceValue);
		}
	
}
