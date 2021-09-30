Feature: Search

@SearchName
Scenario: Search a Name
	Description: User search a name using google search

	Given user navigates to Google Search site
	When user search a name in the Google Search bar
	And user clicks first result
	Then user successfully clicked the first result and landed on the page
