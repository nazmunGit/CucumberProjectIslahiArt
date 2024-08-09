@Cart
Feature: Cart Feature
  As a user i want to add or remove item from the cart
  Background:
    Given User is on home page "https://islahiart.com/"
  @ValidCredentials
  Scenario: Adding item to the cart
    #Given User already open the website IslahiArt
    #When User username as "sarkernazmun@gmail.com" and password as "aBc123456!"
    When User click on BestSelling link
    And User click on item1 "SquareFrame" link into a cart
    Then User navigating to BestSelling link
    And User click on item2 "KidsTee" link into a cart
    Then User should be able to see "SquareFrame" and "KidsTee" on Shopping Cart