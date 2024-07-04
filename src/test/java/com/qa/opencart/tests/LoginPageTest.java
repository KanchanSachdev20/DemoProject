package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opecart.utils.AppConstants;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Login Page Design")
@Story("US 101: design login page for open cart app with title, url, forgot pwd links, user is able to login")
public class LoginPageTest extends BaseTest { 
	
	
	@Severity(SeverityLevel.MINOR)
	@Description("checking login page title test...")
	@Feature("title test")
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("checking login page url test...")
	@Feature("url test")
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertEquals(actUrl, AppConstants.LOGIN_PAGE_FRACTION_URL);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("checking forgot pwd link test...")
	@Feature("forgot pwd test")
	@Test
	public void forgotPwdLinkDisplayedTest() {
		Assert.assertTrue(loginPage.isForgetPwdLinkDisplayed());
	}
	

	@Severity(SeverityLevel.BLOCKER)
	@Description("checking user is able to login with correct username/password test...")
	@Feature("login test")
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
