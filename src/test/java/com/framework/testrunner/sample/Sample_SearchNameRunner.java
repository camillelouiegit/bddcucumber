package com.framework.testrunner.sample;

import com.cucumber.listener.Reporter;
import com.framework.core.Browser;

import com.framework.core.Log;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(features = "classpath:features", plugin = { "pretty", "html:target/cucumberHtmlReport", // html result
		"pretty:target/cucumber-json-report.json", // json result
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/SampleTestRun_Report.html" }, monochrome = true, glue = {
				"com.framework.stepdefinitions.sample" }, tags = { "@SearchName" })

public class Sample_SearchNameRunner extends AbstractTestNGCucumberTests {

	@AfterClass
	public void afterClass() throws Exception {
		// Fetch system details to be used in Extend-Report
		String reportConfigPath = System.getProperty("user.dir").replace("\\", "/")
				+ "/src/main/resources/Config/extent-config.xml";
		Reporter.loadXMLConfig(reportConfigPath);
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Log.setLog("**********************************************************************");
		Log.setLog("*                             End Test                               *");
		Log.setLog("**********************************************************************");
		Browser.closeBrowser();
	}
}