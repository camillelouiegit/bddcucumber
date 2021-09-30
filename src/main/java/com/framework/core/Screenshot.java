package com.framework.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot {

	public static String name;
	public static String path = System.getProperty("user.dir").replace("\\", "/")
			+ "/target/cucumber-reports/screenshots/";

	public static void capture(String filename) {
		File scrFile = ((TakesScreenshot) Browser.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			name = filename + ".png";
			FileUtils.copyFile(scrFile, new File(path + name));
			Log.setScreenshot(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getname() {
		return name;
	}

	public static String getpath() {
		return path;
	}
}
