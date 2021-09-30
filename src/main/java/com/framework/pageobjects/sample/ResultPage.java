package com.framework.pageobjects.sample;

import org.openqa.selenium.By;

import com.framework.webelements.Link;

public class ResultPage {
	public static class ResultList {

		public final static Link firstResult = new Link("first result",
				By.xpath("//a[@href='https://www.leagueoflegends.com/en-us/champions/camille/']"));

		public static void clickFirstResult() {
			firstResult.click();
		}

	}

}
