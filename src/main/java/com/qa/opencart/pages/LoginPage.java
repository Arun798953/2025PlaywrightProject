package com.qa.opencart.pages;

import java.util.Properties;

import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;
	
	private String emailid="//input[@id='input-email']";
	private String password="//input[@id='input-password']";
	private String loginbtn="//input[@value='Login']";
	private String forgotpaswordlink="(//a[normalize-space()='Forgotten Password'][1])";
	private String logoutlink="//a[@class='list-group-item'][normalize-space()='Logout']";
	
	public LoginPage(Page page) {
		this.page=page;
	}
	
	public String getLoginPageTitle() {
		String title = page.title();
		System.out.println("LoginPage Title: " + title);
        return page.title();
	}
	
	public boolean isForgotpwdLinkExist() {
		return page.isVisible(forgotpaswordlink);
	}
	
	public boolean doLogin(String appusername,String apppassword) {
		System.out.println("App credentials: "+appusername +" : "+apppassword);
		page.fill(emailid, appusername);
		page.fill(password, apppassword);
		page.click(loginbtn);
		if(page.isVisible(logoutlink)){
			System.out.println("user is successfully logged in");
			return true;
		}
		return false;
	}

}
