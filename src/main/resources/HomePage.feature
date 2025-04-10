@Navigation
Feature: TC 01 Search for products on Liverpool Page

  Background:
    Given The Client goes to the url
@First
  Scenario: TC 1.1 The user is directed to "Play Station" results
    And The Client write playstation in the searchbar
    And The Client type ENTER
    Then The Client verify that the results displayed include games for PlayStation 5 and PlayStation consoles
    And The Client selects a console in the results listed
    And The Client selects a ps5 in the results
    Then The Client clicks on button add to cart
    And The Client goes to see the shopping-cart
#    When The Client looks at the price
#    Then The Client validate that the price of the product is correct


  Scenario: TC2



