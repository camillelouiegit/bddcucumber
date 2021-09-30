package com.framework.pageobjects.sample;

import org.openqa.selenium.By;

import com.framework.webelements.Element;

public class LandingPage {
	public static class LandingPageHeader {

		public final static Element verifyName = new Element("Camille", By.xpath("//span[contains(text(),'Camille')]"));

		public static void verifyNameIsDisplayed() {
			verifyName.verifyDisplayed();
		}

	}

}