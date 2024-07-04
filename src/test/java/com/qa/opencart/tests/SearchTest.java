package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProviders;

public class SearchTest extends BaseTest {
	
	@BeforeClass
	public void searchSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(dataProvider = "productDataWithSearchKey" , dataProviderClass=ProductDataProviders.class)
	public void productSearchResultCountTest(String searchKey) {
		resPage = accPage.doSearch(searchKey);
		Assert.assertTrue(resPage.getProductResultCount()>0);
	}
	
	@Test(dataProvider = "productDataWithSearchKey", dataProviderClass=ProductDataProviders.class)
	public void searchPageTitleTest(String searchKey) {
		resPage = accPage.doSearch(searchKey);
		String actSearchTitle = resPage.getResultsPageTitle(searchKey);
		System.out.println("search page title" + actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " +searchKey);
	}
	
	@Test(dataProvider = "productDataWithName", dataProviderClass=ProductDataProviders.class)
	public void selectProductTest(String searchKey, String productName) {
		resPage = accPage.doSearch(searchKey);
		productInfoPage = resPage.selectProduct(productName);
		String actualProductHeaderName = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actualProductHeaderName, productName);
	}
	
	@Test(dataProvider = "productDataWithImg", dataProviderClass=ProductDataProviders.class)
	public void productImagesTest(String searchKey, String productName, int imgCount) {
		resPage = accPage.doSearch(searchKey);
		productInfoPage = resPage.selectProduct(productName);
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount, imgCount);
	}
}
