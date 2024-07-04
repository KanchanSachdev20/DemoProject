package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opecart.utils.AppConstants;
import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void logoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void myAccountLinkExist() {
		Assert.assertTrue(accPage.isMyAccountLinkExist());
	}
	@Test
	public void accPaheHeaderCountTest() {
		Assert.assertEquals(accPage.getAccountPageHeaderList().size() ,4);
	}
	
	@Test
	public void accPageHeaderTest() {
		List<String> accHeaderList = accPage.getAccountPageHeaderList();
	    Assert.assertEquals(accHeaderList, AppConstants.EXPECTED_ACCT_HEADERS_LIST);
	}
	
	
}
