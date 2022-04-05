package com.framework.pageobjects.sample;

import org.openqa.selenium.By;

import com.framework.core.Browser;
import com.framework.webelements.Element;
import com.framework.webelements.TextBox;

public class GoogleSearchPage {

	public static class SearchBar extends Browser {

		public final static TextBox searchBar = new TextBox("Google Search bar", By.xpath("//input[@name='q']"));
		public final static Element googleSearchButton = new Element("Google Search bar",
				By.xpath("//div[2]//input[@name='btnK']"));

		public static void verifySearchBar() {
			searchBar.verifyDisplayed();
		}

		public static void enterNameToSearch(String Name) {
			searchBar.setText(Name);
		}

		public static void clickGoogleSearchButton() {
			googleSearchButton.click();
		}

	}

}