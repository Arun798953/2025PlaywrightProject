package com.qa.opencart.base;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	PlaywrightFactory pf;
	Page page;
	protected Properties prop;
	
	protected HomePage homepage;
	protected LoginPage loginpage;

	
	@BeforeTest()
		public void setup() throws IOException {
			pf=new PlaywrightFactory();
			prop=pf.initProp();
			page=pf.initBrowser(prop);
			homepage=new HomePage(page);
	}

	@AfterTest()
	public void teardown() {
	//page.context().browser().close();
		
	}


}
