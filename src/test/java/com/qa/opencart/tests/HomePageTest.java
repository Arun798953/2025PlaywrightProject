package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Appconstants;

public class HomePageTest extends BaseTest {

	@Test()
	public void validateHomepageTitleTest() {
		String actualtitle = homepage.getHomePageTitle();
		Assert.assertEquals(actualtitle, Appconstants.HOME_PAGE_TITLE);
	}

	@Test()
	public void validateHomepageUrlTest() {
		String actualurl = homepage.getHomePageUrl();
		Assert.assertEquals(actualurl, Appconstants.HOME_PAGE_URL);
	}

	@DataProvider()
	public Object getData()[][] {
		return new Object[][] { { "Macbook" }, { "Samsung" }, { "iMac" }, };

	}

	@Test(dataProvider = "getData")
	public void validateSearchHeaderTest(String Productname) {
		String actualheader = homepage.getSearchHeader(Productname);
		Assert.assertEquals(actualheader, "Search - " + Productname);
	}

}
