package com.framework.webelements;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.core.Browser;
import com.framework.core.Log;

public class Button extends Browser {

	private By by;
	private WebElement element;
	private String name;

	public Button(String name, By by) {
		this.by = by;
		this.name = name;
	}

	public void click() {
		element = Browser.findElement(by);
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 10);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			for (int i = 0; i <= 2; i++) {
				try {
					int count = 0;
					while (!element.isDisplayed() && count != 5) {
						delay(1);
						count++;
					}
					wait.ignoring(StaleElementReferenceException.class)
							.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					Log.testStep("PASSED", name + " Button is clicked", name + " Button is clicked");
					break;
				} catch (Exception e) {
					Log.setLog(StringUtils.substringBefore(e.getMessage(), ":"));
					delay(1);
					element = Browser.findElement(by);
					wait.ignoring(StaleElementReferenceException.class)
							.until(ExpectedConditions.elementToBeClickable(element));
				}
			}
		}
	}

	public void doubleClick() {
		element = Browser.findElement(by);
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 10);
		if (element == null) {
			Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
		} else {
			int count = 0;
			while (!element.isDisplayed() && count != 5) {
				delay(1);
				count++;
			}
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
			Actions action = new Actions(Browser.getDriver());
			action.moveToElement(element);
			action.doubleClick();
			action.perform();
			Log.testStep("PASSED", name + " is double clicked", name + " is double clicked");
		}
	}

	public void clickJS() {
		element = Browser.findElement(by);
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 10);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			int count = 0;
			while (!element.isDisplayed() && count != 5) {
				delay(1);
				count++;
			}
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
			JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
			executor.executeScript("arguments[0].click();", element);
			Log.testStep("PASSED", name + " Button is clicked", name + " Button is clicked");
		}
	}

	public void clickFromHover() {
		element = Browser.findElement(by);
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 10);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			int count = 0;
			while (!element.isDisplayed() && count != 5) {
				delay(1);
				count++;
			}
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
			Actions builder = new Actions(Browser.getDriver());
			builder.moveToElement(element).build().perform();
			element.click();
			Log.testStep("PASSED", name + " Button is clicked", name + " Button is clicked");
		}
	}

	public void clickFromHoverJS() {
		element = Browser.findElement(by);
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 10);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			int count = 0;
			while (!element.isDisplayed() && count != 5) {
				delay(1);
				count++;
			}
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) driver).executeScript(mouseOverScript, element);
			element.click();
			Log.testStep("PASSED", name + " Button is clicked", name + " Button is clicked");
		}
	}

	public String getAttribute(String attribute) {
		element = Browser.findElement(by);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
			return null;
		} else {
			return element.getAttribute(attribute);
		}
	}

	public String getText() {
		element = Browser.findElement(by);
		if (element == null) {
			return null;
		} else {
			return element.getText();
		}
	}

	public boolean isAttributeContains(String attribute, String attributeValue) {
		element = Browser.findElement(by);
		if (element == null) {
			return false;
		} else {
			return element.getAttribute(attribute).contains(attributeValue);
		}
	}

	public boolean isAttributeEquals(String attribute, String attributeValue) {
		element = Browser.findElement(by);
		if (element == null) {
			return false;
		} else {
			return element.getAttribute(attribute).equals(attributeValue);
		}
	}

	public boolean isDisplayed() {
		element = Browser.findElement(by);
		if (element == null) {
			return false;
		} else {
			return element.isDisplayed();
		}
	}

	public boolean isEnabled() {
		element = Browser.findElement(by);
		if (element == null) {
			return false;
		} else {
			return element.isEnabled();
		}
	}

	public boolean isSelected() {
		element = Browser.findElement(by);
		if (element == null) {
			return false;
		} else {
			return element.isSelected();
		}
	}

	public boolean isTextContains(String text) {
		element = Browser.findElement(by);
		if (element == null) {
			return false;
		} else {
			return element.getText().contains(text);
		}
	}

	public boolean isTextEquals(String text) {
		element = Browser.findElement(by);
		if (element == null) {
			return false;
		} else {
			return element.getText().equals(text);
		}
	}

	public void submit() {
		element = Browser.findElement(by);
		if (element == null) {
			Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
		} else {
			Log.testStep("PASSED", name + " is submitted", name + " is submitted");
			element.submit();
		}
	}

	public void verifyAttributeContains(String attribute, String attributeValue) {
		element = Browser.findElement(by);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			if (element.getAttribute(attribute).contains(attributeValue)) {
				Log.testStep("PASSED", name + " Button attribute contains " + attributeValue,
						name + " Button attribute contains " + attributeValue);
			} else {
				Log.testStep("FAILED", name + " Button attribute NOT contains " + attributeValue,
						name + " Button attribute contains " + attributeValue);
			}
		}
	}

	public void verifyAttributeEquals(String attribute, String attributeValue) {
		element = Browser.findElement(by);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			if (element.getAttribute(attribute).equals(attributeValue)) {
				Log.testStep("PASSED", name + " Button attribute is equal to " + attributeValue,
						name + " Button attribute is equal to " + attributeValue);
			} else {
				Log.testStep("FAILED", name + " Button attribute is NOT equal to " + attributeValue,
						name + " Button attribute is equal to " + attributeValue);
			}
		}
	}

	public void verifyDisplayed() {
		element = Browser.findElement(by);
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 10);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			Log.testStep("PASSED", name + " Button is displayed", name + " Button is displayed");
		}
	}

	public void verifyNotDisplayed() {
		element = Browser.findElement(by);
		if (element == null) {
			Log.testStep("PASSED", name + " Button is NOT displayed", name + " Button is NOT displayed");
		} else {
			try {
				WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 5);
				wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
				Log.testStep("PASSED", name + " Button is NOT displayed", name + " Button is NOT displayed");
			} catch (Exception e) {
				Log.testStep("FAILED", name + " Button is displayed", name + " Button is NOT displayed");
			}
		}
	}

	public void verifyTextContains(String text) {
		element = Browser.findElement(by);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			if (element.getText().contains(text)) {
				Log.testStep("PASSED", name + " Button text contains " + text, name + " Button text contains " + text);
			} else {
				Log.testStep("FAILED", name + " Button text NOT contains " + text,
						name + " Button text contains " + text);
			}
		}
	}

	public void verifyTextEquals(String text) {
		element = Browser.findElement(by);
		if (element == null) {
			Log.testStep("FAILED", name + " Button is NOT displayed", name + " Button is displayed");
		} else {
			if (element.getText().equals(text)) {
				Log.testStep("PASSED", name + " Button text is equal to " + text,
						name + " Button text is equal to " + text);
			} else {
				Log.testStep("FAILED", name + " Button text is NOT equal to " + text,
						name + " Button text is equal to " + text);
			}
		}
	}

	public void verifyNotVisible() {
		element = Browser.findElement(by);
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 20);
		try {
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
			Log.testStep("PASSED", name + " Button is not visible", name + " Button is not visible");
		} catch (Exception e) {
			Log.setLog(StringUtils.substringBefore(e.getMessage(), ":"));
			delay(1);
			element = Browser.findElement(by);
			wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
					.until(ExpectedConditions.invisibilityOf(element));

		}
	}

}
