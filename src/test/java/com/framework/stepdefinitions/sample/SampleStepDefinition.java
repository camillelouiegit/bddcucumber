package com.framework.stepdefinitions.sample;

import com.framework.core.Browser;
import com.framework.pageobjects.sample.GoogleSearchPage;
import com.framework.pageobjects.sample.LandingPage;
import com.framework.pageobjects.sample.ResultPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleStepDefinition {
	@Given("^user navigates to Google Search site$")
	public void NavigateToGoogleSearch() throws Exception {
		Browser.InitializeTest();
	}

	@When("^user search a name in the Google Search bar$")
	public void SearchAndClick() {
		GoogleSearchPage.SearchBar.enterNameToSearch("Camille");
		GoogleSearchPage.SearchBar.clickGoogleSearchButton();
	}

	@And("user clicks first result")
	public void clickFirstResult() {
		ResultPage.ResultList.clickFirstResult();
	}

	@Then("^user successfully clicked the first result and landed on the page$")
	public void VerifyNameIsDisplayed() {
		LandingPage.LandingPageHeader.verifyNameIsDisplayed();
	}

}