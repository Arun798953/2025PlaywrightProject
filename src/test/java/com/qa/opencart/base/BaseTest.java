package com.qa.opencart.base;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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

	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional String browserName) throws IOException {
		pf = new PlaywrightFactory();

		prop = pf.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		page = pf.initBrowser(prop);
		 homepage=new HomePage(page); 


	}
	
	@AfterTest
	public void teardown() {
	page.context().browser().close();
		
	}


}
