package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	private Page page;
	
	private String search = "input[name='search']";
	private String searchicon = "div#search button";
	private String ProductHeader="div#content h1";
	private String loginlink="//a[normalize-space()='Login']";
	private String myAccount="//span[normalize-space()='My Account']";

	public HomePage(Page page) {
		this.page = page;
	}

	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("HomePage Title: " + title);
		return page.title();
	}

	public String getHomePageUrl() {
		String url = page.url();
		System.out.println("HomePage Url: " + url);
		return page.url();
	}

	public String getSearchHeader(String productname) {
		page.fill(search, productname);
		page.click(searchicon);
		String header= page.textContent(ProductHeader);
		System.out.println("Search Header: "+header);
		return header;
	}
	
	public LoginPage navigatetoLoginPage() {
		page.click(myAccount);
		page.click(loginlink);
		return new LoginPage(page);
		
	}


}
