package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opecart.utils.AppConstants;
import com.qa.opecart.utils.ElementUtils;

public class AccountsPage {

private WebDriver driver;
private ElementUtils ele;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtils(driver);
	}
	
	private By myAccLink = By.linkText("My Account");
	private By logoutLink = By.linkText("Logout");
	private By acctHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public String getAccPageTitle() {
		return ele.waitForTitleIsAndCapture(AppConstants.ACCOUNT_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public boolean isLogoutLinkExist() {
		 return ele.checkElementIsDisplayed(logoutLink);
	 }
	
	public boolean isMyAccountLinkExist() {
		 return ele.checkElementIsDisplayed(myAccLink);
	 }
	
	public List<String> getAccountPageHeaderList() {
		 List<WebElement> accountHeaderList= ele.waitForElementsVisible(acctHeaders,AppConstants.MEDIUM_DEFAULT_VALUE);
		 List<String> accountHeaderTextList = new ArrayList<String>();
		 for(WebElement e: accountHeaderList) {
			 String text = e.getText();
			 accountHeaderTextList.add(text);
		 }
		 return accountHeaderTextList;
	 }
	
	public ResultsPage doSearch(String productName) {
		ele.waitForElementVisible(search, AppConstants.MEDIUM_DEFAULT_VALUE);
		ele.doSendKeys(search, productName);
		ele.doClick(searchIcon);
		return new ResultsPage(driver);
	}
}
