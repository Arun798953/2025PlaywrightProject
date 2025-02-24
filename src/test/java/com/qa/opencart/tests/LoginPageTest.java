package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Appconstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void validateLoginNavigationTest() {
		loginpage = homepage.navigatetoLoginPage();
		String acttitle = loginpage.getLoginPageTitle();
		System.out.println("LoginPage title: " + acttitle);
		Assert.assertEquals(acttitle, Appconstants.LOGIN_PAGE_TITLE);

	}

	@Test(priority = 2)
	public void forgotPasswordLinkTest() {
		Assert.assertTrue(loginpage.isForgotpwdLinkExist());
	}

	@Test(priority = 3)
	public void appLoginTest() {
		Assert.assertTrue(loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
	}
}
