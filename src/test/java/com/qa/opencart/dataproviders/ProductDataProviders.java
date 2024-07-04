package com.qa.opencart.dataproviders;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.Product;

public class ProductDataProviders {

	@DataProvider(name = "productData")
	public Object[][] getProductTestData() {
		return new Object[][] {
			{new Product("Macbook","MacBook Pro",4)},
			{new Product("iMac","iMac",3)},
			{new Product("Samsung","Samsung SyncMaster 941BW",1)}
		};
	}
	
	@DataProvider(name = "productDataWithSearchKey")
	public Object[][] getProductNameData() {
		return new Object[][] {
			
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	
	@DataProvider(name = "productDataWithName")
	public Object[][] getProductData() {
		return new Object[][] {
			
			{"Macbook", "MacBook Pro"},
			{"iMac" , "iMac"},
			{"Samsung","Samsung SyncMaster 941BW"}
		};
	}
	
	@DataProvider(name = "productDataWithImg")
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			
			{"Macbook", "MacBook Pro" , 4},
			{"iMac" , "iMac" , 3},
			{"Samsung","Samsung SyncMaster 941BW", 1}
		};
	}
}
