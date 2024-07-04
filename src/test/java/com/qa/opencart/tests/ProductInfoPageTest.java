package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void searchSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoTest() {
		resPage = accPage.doSearch("Macbook");
		productInfoPage = resPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap = productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Availability"), "In Stock");
	}
}
