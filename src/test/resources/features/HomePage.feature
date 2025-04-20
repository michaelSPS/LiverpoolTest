@Navigation
Feature: TC 01 Search for products on Liverpool Page

  Background:
    Given The Client navigate to home-page
@First
  Scenario: TC 1.1 The user is directed to "Play Station" results
    When The Client write playstation in the searchbar
    And The Client type ENTER
    Then The Client verify that the results displayed include games for PlayStation 5 and PlayStation consoles
    And The Client selects a console in the results listed
    And The Client selects a ps5 in the results
    And The Client clicks on button add to cart
    And The Client clicks add warrentie
    And The Client goes to see the shopping-cart
    Then The Client must be able to see the playstation 5 on the shopping cart

  @Second
  Scenario: TC2 The user is directed to "Smart TV" results
    When The Client search for smart-tv in the searchbar
    And The Client type ENTER on searchbar
    Then The Client validate that the Size and Price filters are displayed
    When The Client filters the results by size: 55 inches, price: > 10,000, brand: sony
    Then The Client validate the results count





