Feature: Navigate to different sites

@smokeTest
Scenario Outline: Go to Google and search different sites
Given Go to the google URL 
When user enter text to search "<URL>" in search text box
And click on first link
Then Clicked link HomePage is displayed take screenshot and attach to Extent report


Examples:
    |   URL   		|
    |SAP FIORI trial|
    | eclipse 		|

@RegressionTest
Scenario Outline: Go to Google and search different sites
Given Go to the google URL 
When user enter text to search "<URL>" in search text box
And click on first link
Then Clicked link HomePage is displayed take screenshot and attach to Extent report


Examples:
    |   URL   		|
    |	Java		|
