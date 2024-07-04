package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProviders;
import com.qa.opencart.pojo.Product;

public class SearchDataTest extends BaseTest {

	@BeforeClass
	public void searchSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class)
	public void productSearchResultCountTest(Product product) {
		resPage = accPage.doSearch(product.getSearchKey());
		Assert.assertTrue(resPage.getProductResultCount()>0);
	}
	
	@Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class)
	public void searchPageTitleTest(Product product) {
		resPage = accPage.doSearch(product.getSearchKey());
		String actSearchTitle = resPage.getResultsPageTitle(product.getSearchKey());
		System.out.println("search page title" + actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " +product.getSearchKey());
	}
	
	@Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class)
	public void selectProductTest(Product product) {
		resPage = accPage.doSearch(product.getSearchKey());
		productInfoPage = resPage.selectProduct(product.getProductName());
		String actualProductHeaderName = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actualProductHeaderName, product.getProductName());
	}
	
	@Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class)
	public void productImagesTest(Product product) {
		resPage = accPage.doSearch(product.getSearchKey());
		productInfoPage = resPage.selectProduct(product.getProductName());
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount, product.getProductImg());
	}
}
