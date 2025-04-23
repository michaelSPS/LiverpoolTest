@smoke
Feature: Smoke Test - Basic checks

  Scenario: Homepage loads successfully
    Given The user navigates to the homepage
    Then The homepage should be displayed
