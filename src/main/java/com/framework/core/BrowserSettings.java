package com.framework.core;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cucumber.listener.Reporter;

public interface BrowserSettings {
	@BeforeClass
	public default void beforeClass() throws Exception {
		Browser.closeBrowser();
	}

	@AfterClass
	public default void afterClass() throws Exception {
		// Fetch system details to be used in Extend-Report
		String reportConfigPath = System.getProperty("user.dir").replace("\\", "/")
				+ "/src/main/resources/Config/extent-config.xml";
		Reporter.loadXMLConfig(reportConfigPath);
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));

		Browser.closeBrowser();
	}

}