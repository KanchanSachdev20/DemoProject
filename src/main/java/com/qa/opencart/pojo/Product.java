package com.qa.opencart.pojo;

public class Product {
	
	private String searchKey;
	private String productName;
	private int productImg;
	

	public Product(String searchKey, String productName, int productImg) {
		this.searchKey = searchKey;
		this.productName = productName;
		this.productImg = productImg;
	}
	
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductImg() {
		return productImg;
	}
	public void setProductImg(int productImg) {
		this.productImg = productImg;
	}

	@Override
	public String toString() {
		return "Product [searchKey=" + searchKey + ", productName=" + productName + ", productImg=" + productImg + "]";
	}
	

}
