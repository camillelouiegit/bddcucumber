package com.framework.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.cucumber.listener.Reporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Browser {

	public static String browser;
	private static String url;
	public static String downloadFilepath = System.getProperty("user.dir").replace("\\", "/")
			+ "/src/main/resources/downloads/";
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static String environment = System.getProperty("environment");
	private static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();
	public static Integer searchLoop = 1;
	public static String seleniumGridUrl = System.getProperty("webdriver.base.url");
	public static FileInputStream fileInputStream;
	public static Properties properties;
	public static ITestResult result;
	public static String browserName;

	public static void getBrowser(String chooseBrowser) throws Exception {
		fileInputStream = new FileInputStream(
				System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/AppConfig/config.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		browser = properties.getProperty("browser").toString();
	}

	public static void closeBrowser() throws Exception {
		if (getDriver() != null) {
			getDriver().close();
			getDriver().quit();
			Log.setLog(browser.replace("grid_", "") + " instance closed.");
		}
	}

	public static void delay(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}

	public static WebElement findElement(By by) throws WebDriverException {
		WebElement element = null;
		int i = 0;
		int attempts = 0;
		while (i != searchLoop && element == null) {
			i += 1;
			try {
				element = new WebDriverWait(getDriver(), 10).ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfElementLocated(by));
			} catch (Exception e) {
				element = null;
			}
		}
		if (element != null) {
			try {
				while (attempts < 2) {
					try {
						Actions actions = new Actions(getDriver());
						actions.moveToElement(element);
						actions.perform();
					} catch (StaleElementReferenceException e) {
					}
					attempts++;
				}
			} catch (Exception e) {
			}
		}

		return element;
	}

	public static List<WebElement> findElements(By by) throws WebDriverException {
		List<WebElement> elements = null;
		int i = 0;
		int attempts = 0;
		while (i != searchLoop && elements.size() == 0) {
			i += 1;
			try {
				elements = new WebDriverWait(getDriver(), 5).ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			} catch (Exception e) {
				elements = null;
			}
		}
		if (elements != null) {
			try {
				while (attempts < 2) {
					try {
						Actions actions = new Actions(getDriver());
						actions.moveToElement(elements.get(0));
						actions.perform();
					} catch (StaleElementReferenceException e) {
					}
					attempts++;
				}
			} catch (Exception e) {
			}
		}
		return elements;
	}

	public static List<WebElement> findElements(By parentBy, By childBy) throws WebDriverException {
		WebElement element = null;
		List<WebElement> elements = null;
		int i = 0;
		int attempts = 0;
		while (i != searchLoop && elements.size() == 0) {
			i += 1;
			try {
				element = new WebDriverWait(getDriver(), 5).ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfElementLocated(parentBy));
				elements = element.findElements(childBy);
			} catch (Exception e) {
				elements = null;
			}
		}
		if (elements != null) {
			try {
				while (attempts < 2) {
					try {
						Actions actions = new Actions(getDriver());
						actions.moveToElement(elements.get(0));
						actions.perform();
					} catch (StaleElementReferenceException e) {
					}
					attempts++;
				}
			} catch (Exception e) {
			}
		}
		return elements;
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static ExtentTest getLogger() {
		return logger.get();
	}

	public static int getTabCount() {
		return new ArrayList<String>(getDriver().getWindowHandles()).size();
	}

	@SuppressWarnings("deprecation")
	public static void openBrowser() throws Exception {
		getBrowser(browser);
		if (browser.equalsIgnoreCase("firefox")) {
			// Set Path for the executable file
			System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
			System.setProperty("webdriver.firefox.marionette", "true");

			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					System.getProperty("user.dir").replace("\\", "/") + "marionette_logs.txt");

			HashMap<String, Object> firefoxPrefs = new HashMap<String, Object>();
			firefoxPrefs.put("profile.default_content_settings.popups", 0);
			firefoxPrefs.put("download.default_directory", downloadFilepath);
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("prefs", firefoxPrefs);

			fileInputStream = new FileInputStream(System.getProperty("user.dir").replace("\\", "/")
					+ "/src/main/resources/AppConfig/config.properties");
			properties = new Properties();
			properties.load(fileInputStream);
			String firefoxProfile = properties.getProperty("firefox_profile").toString();
			if (firefoxProfile.equals("true")) {
				firefoxOptions.setCapability("excludeSwitches", Collections.singletonList("enable-automation"));
				firefoxOptions.setCapability("useAutomationExtension", false);
			} else {
				Log.testStep("INFO", "Firefox Profile not detected", "Firefox Profile not detected");
			}
			firefoxOptions.setHeadless(true);
			firefoxOptions.addArguments("--width=1920");
			firefoxOptions.addArguments("--height=1080");
			firefoxOptions.addPreference("media.navigator.streams.fake", true);

			DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
			firefoxCapabilities.setJavascriptEnabled(true);
			firefoxCapabilities.setAcceptInsecureCerts(true);
			firefoxCapabilities.setCapability("marionette", false);
			firefoxCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			firefoxCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);

			Log.testStep("INFO", "Browser used is : " + firefoxCapabilities.getBrowserName().toString(),
					firefoxCapabilities.getBrowserName().toString());
			browserName = firefoxCapabilities.getBrowserName().toString();

			driver.set(new FirefoxDriver(firefoxCapabilities));
			getDriver().manage().window().maximize();

		}else if (browser.equalsIgnoreCase("chrome")) {
			String driverType = null;
			if (OSChecker.isWindows()) {
				driverType = "chromedriver.exe";
			} else if (OSChecker.isMac()) {
				driverType = "chromedriver_mac";
			}

			System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/" + driverType);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);

			fileInputStream = new FileInputStream(System.getProperty("user.dir").replace("\\", "/")
					+ "/src/main/resources/AppConfig/config.properties");
			properties = new Properties();
			properties.load(fileInputStream);
			String chromeProfile = properties.getProperty("chrome_profile").toString();
			if (chromeProfile.equals("true")) {
				Log.testStep("INFO", "Initializing Chrome Profile", "Initializing Chrome Profile");
				chromeOptions.addArguments("disable-infobars", "--disable-web-security",
						"--no-proxy-server", "--incognito", "--disable-gpu");
				chromeOptions.addArguments("start-maximized");
				chromeOptions.addArguments("mute-audio");
				chromeOptions.addArguments("disable-extensions");
//				chromeOptions.addArguments("headless");
				chromeOptions.addArguments("disable-gpu");
				chromeOptions.addArguments("window-size=1920x1080");
				chromeOptions.setExperimentalOption("prefs", chromePrefs);
				chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				chromeOptions.setExperimentalOption("useAutomationExtension", false);
				DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
				chromeCapabilities.setJavascriptEnabled(true);
				chromeCapabilities.setAcceptInsecureCerts(true);
				chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

				Log.testStep("INFO", "Browser used is : " + chromeCapabilities.getBrowserName().toString(),
						chromeCapabilities.getBrowserName().toString());
				browserName = chromeCapabilities.getBrowserName().toString();

				driver.set(new ChromeDriver(chromeCapabilities));

				Log.testStep("PASSED", "Browser Initialized", "Browser Initialized");
			} else {
				Log.testStep("INFO", "Chrome Profile not detected", "Chrome Profile not detected");
			}

		}
		// getDriver().manage().deleteAllCookies();
		Log.setLog("Start automation test in " + browser.replace("grid_", "") + " with Dimensions: "
				+ getDriver().manage().window().getSize());
	}

	public static String getBrowserProperty() throws Exception {
		fileInputStream = new FileInputStream(
				System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/AppConfig/config.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		String browserValue = properties.getProperty("browser").toString();
		return browserValue;

	}

	public static void setEnvironment() throws Exception {
		fileInputStream = new FileInputStream(
				System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/AppConfig/config.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		environment = properties.getProperty("environment").toString();

		if (environment.equals("dev")) {
			Log.testStep("INFO", "Setting the environment to " + environment,
					"Setting the environment to " + environment);
		} else if (environment.equals("test")) {
			Log.testStep("INFO", "Setting the environment to " + environment,
					"Setting the environment to " + environment);
		}

	}

	public static void openApplication() throws Exception {
		fileInputStream = new FileInputStream(
				System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/AppConfig/config.properties");
		properties = new Properties();
		properties.load(fileInputStream);

		if (environment.equals("dev")) {
			url = properties.getProperty("dev_url").toString();
			Log.testStep("INFO", "Accessing " + url, "Accessing " + url);
		} else if (environment.equals("test")) {
			url = properties.getProperty("test_url").toString();
			Log.testStep("INFO", "Accessing " + url, "Accessing " + url);
		}
		getDriver().get(url);

	}

	private static void CleanTestOutput() throws IOException {
		// Clean previous generated report
		File destination = new File(System.getProperty("user.dir").replace("\\", "/") + "/test-output");
		FileUtils.deleteDirectory(destination);
		Log.testStep("INFO", "Cleaning previous logs..", " Cleaning previous logs..");
	}

	public static void InitializeTest() throws Exception {
		CleanTestOutput();
		getBrowserProperty();
		setEnvironment();
		openBrowser();
		openApplication();
	}

}
