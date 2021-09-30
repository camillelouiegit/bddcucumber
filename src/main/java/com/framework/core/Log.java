package com.framework.core;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.cucumber.listener.Reporter;

public class Log {

	private static Logger consoleLog = Logger.getLogger(Log.class.getName());

	public static void scenarioLog(String name) {
		Reporter.addScenarioLog("[Scenario:] " + name);
		consoleLog.info("[Scenario:] " + name);
	}

	public static void setLog(String name) {
		Reporter.addScenarioLog(name);
		consoleLog.info(name);
	}

	public static void setScreenshot(String name) throws IOException {
		Reporter.addScreenCaptureFromPath(Screenshot.path + name);
		consoleLog.info("[SCREENSHOT] " + name);
	}

	public static void testStep(String tag, String Actual, String Expected) {

		if (tag.toUpperCase() == "PASSED") {
			Reporter.addStepLog("[" + tag + "] " + Actual);
			consoleLog.info("[" + tag + "] " + Actual);
		} else if (tag.toUpperCase() == "FAILED") {
			Reporter.addStepLog("[" + tag + "] Expected: " + Expected);
			Reporter.addStepLog("[" + tag + "] Actual: " + Actual);
			consoleLog.warn("[" + tag + "] Expected: " + Expected);
			consoleLog.warn("[" + tag + "] Actual: " + Actual);
			Assert.fail(Actual);
		} else if (tag.toUpperCase() == "WARNING") {
			Reporter.addStepLog("[" + tag + "] " + Actual);
			consoleLog.warn("[" + tag + "] " + Actual);
		} else {
			Reporter.addStepLog("[" + tag + "] " + Actual);
			consoleLog.info("[" + tag + "] " + Actual);
		}
	}

}
