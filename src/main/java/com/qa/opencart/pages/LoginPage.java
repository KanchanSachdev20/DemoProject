package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opecart.utils.AppConstants;
import com.qa.opecart.utils.ElementUtils;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtils ele;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtils(this.driver);
	}
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By footerLinks = By.xpath("//footer//a");
	private By errorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	private By registerLink = By.linkText("Register");
	
	public String getLoginPageTitle() {
		return ele.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public String getLoginPageUrl() {
		return ele.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	 public boolean isForgetPwdLinkDisplayed() {
		 return ele.checkElementIsDisplayed(forgetPwdLink);
	 }
	 
	 public List<String> getFooterLinkText() {
		 List<WebElement> footerLinksList= ele.waitForElementsVisible(footerLinks,AppConstants.MEDIUM_DEFAULT_VALUE);
		 List<String> footerTextList = new ArrayList<String>();
		 for(WebElement e: footerLinksList) {
			 String text = e.getText();
			 footerTextList.add(text);
		 }
		 return footerTextList;
	 }
	 
	 
	  public AccountsPage doLogin(String username, String pwd) {
		 ele.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_VALUE).sendKeys(username);
		 ele.doSendKeys(password, pwd);
		 ele.doClick(loginBtn);
		  return new AccountsPage(driver);
	 }
	  
	  public boolean doLoginWithWrongCredentials(String username, String pwd){
		  System.out.println("wrong creds are :" + username + ":" + pwd);
	 	  ele.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_VALUE);
	 	  ele.doSendKeys(emailId, username);
			 ele.doSendKeys(password, pwd);
			 ele.doClick(loginBtn);
			 String errMsg = ele.doGetElementText(errorMsg);
			 System.out.println(errMsg);
			 if(errMsg.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
				 return true;
			 }
			 return false;
	  }
	  
	  public RegisterPage navigateToRegisterPage() {
			ele.doClick(registerLink);
			return new RegisterPage(driver);
		}
}
