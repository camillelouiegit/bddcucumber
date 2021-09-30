package com.framework.testrunner.sample;

import com.framework.core.BrowserSettings;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "classpath:features", plugin = { "pretty", "html:target/cucumberHtmlReport", // html result
		"pretty:target/cucumber-json-report.json", // json result
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/SampleTestRun_Report.html" }, monochrome = true, glue = {
				"com.framework.stepdefinitions.sample" }, tags = { "@SearchName" })

public class Sample_SearchNameRunner extends AbstractTestNGCucumberTests implements BrowserSettings {

}