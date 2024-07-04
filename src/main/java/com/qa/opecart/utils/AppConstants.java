package com.qa.opecart.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE_VALUE = "My Account";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String Account_PAGE_FRACTION_URL = "Account Login";
	
	public static final int SHORT_DEFAULT_WAIT = 5;
	public static final int MEDIUM_DEFAULT_VALUE = 10;
	public static final int LONG_DEFAULT_VALUE = 20;
	
	public static final List<String> EXPECTED_ACCT_HEADERS_LIST = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final String LOGIN_ERROR_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";
	public static final String USER_RESG_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";
}
