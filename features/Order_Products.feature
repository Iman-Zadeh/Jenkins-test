@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag2.1
  Scenario: Order Blue Jeans
    Given I am on the Home Page
    And I want to order '25' 'Blue Jeans'
    When I complete purchase
    Then I verify the success message
  