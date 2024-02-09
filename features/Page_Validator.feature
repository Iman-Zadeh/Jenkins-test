@tag
Feature: Page Validator
  I want validate all the main pages

  @tag3.1
  Scenario Outline: Validate all the main pages
    Given I am on the Home Page
    When I click on '<tabs>'
    Then I validate that page navigates to '<url>' and title contains '<title>'

    Examples: 
      | tabs              | url                                                 | title             |
      | BOOKS             | https://demowebshop.tricentis.com/books             | Books             |
      | COMPUTERS         | https://demowebshop.tricentis.com/computers         | Computers         |
      | ELECTRONICS       | https://demowebshop.tricentis.com/electronics       | Electronics       |
      | APPAREL & SHOES   | https://demowebshop.tricentis.com/apparel-shoes     | Apparel & Shoes   |
      | DIGITAL DOWNLOADS | https://demowebshop.tricentis.com/digital-downloads | Digital downloads |
      | JEWELRY           | https://demowebshop.tricentis.com/jewelry           | Jewelry           |
      | GIFT CARDS        | https://demowebshop.tricentis.com/gift-cards        | Gift Cards        |
