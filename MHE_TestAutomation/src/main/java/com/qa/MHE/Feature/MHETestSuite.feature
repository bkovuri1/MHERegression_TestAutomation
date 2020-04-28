Feature: MHE Regression Suite
Scenario: Open MHE Site URL and verify if Introductory message is displaying
Given Navigate to MHE Home Page
When MHE site loaded 
Then Verify if introductory message is displayed

Scenario: Intro message should not display in other pages
Given Navigate to About Page
When page loaded
Then Verify intro message is not displaying

Scenario: Publisher logo is displayed in Header Page
Given Navigate to MHE Home Page
When MHE site loaded
Then Verify Publisher logo is displayed
And Navigate to About Page
When page loaded
Then Verify Publisher logo is displayed

Scenario: Verify if user clicks on MHE Logo then https://www.mheducation.com/ opens in a new tab/window
Given Navigate to MHE Home Page
When MHE site loaded
Then Verify mheeducation.com opened in new window

Scenario: Verify if Site logo is displayed in the HomePage
Given Navigate to MHE Home Page
When Verify Sitelogo is displayed and Click

Scenario: Intro message should not display in other pages
Given Navigate to About Page
When Verify Sitelogo is displayed and Click
Then Verify HomePage is Loaded

