Feature: Website language change as Admin

  As an Admin I want to change the language from English to German on the whole Orange HRM Website

  Scenario: Website language change
    Given User is on the login page "https://opensource-demo.orangehrmlive.com/"
    When User logs in as Admin by using login "Admin" and password "admin123"
    And User chooses Configuration option from the Admin menu
    And User chooses Location option from the Configuration menu
    And User clicks on the Edit button
    And User chooses German language from the dropdown list
    And User click on the Save button
    Then Website language changes into German

