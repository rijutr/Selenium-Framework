@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
    Given I landed on ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message displayed

    Examples:
      | username     | password |
      | test@cts.com | Unni@1123 |