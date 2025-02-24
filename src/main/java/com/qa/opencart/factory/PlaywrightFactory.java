package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	Playwright playwright;
	Browser browser;
	BrowserContext browsercontext;
	Page page;
	Properties prop;
	private static ThreadLocal<Playwright> tlplaywright = new ThreadLocal<>();
	private static ThreadLocal<Browser> tlbrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlbrowsercontext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlpage = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tlplaywright.get();
	}

	public static Browser getBrowser() {
		return tlbrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlbrowsercontext.get();
	}

	public static Page getPage() {
		return tlpage.get();
	}

	public Page initBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is: " + browserName);

		// playwright = Playwright.create();
		tlplaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
			tlbrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "firefox":
			tlbrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "safari":
			tlbrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "chrome":
			tlbrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;

		case "edge":
			tlbrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;
		default:
			System.out.println("Please pass the right browser: " + browserName);
			break;
		}

		tlbrowsercontext.set(getBrowser().newContext());
		tlpage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());

		return getPage();
	}

	/**
	 * this method is used to intiliaze config files
	 * 
	 * @return
	 * @throws IOException
	 */
	public Properties initProp() {
		try {
			FileInputStream fip = new FileInputStream("./src/test/resource/config/config.properties");
			prop = new Properties();
			try {
				prop.load(fip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

//		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
//		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return path;
	}

}
