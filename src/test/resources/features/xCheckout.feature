@xCheckout
Feature: xCheckout feature
  As a User I want to checkout items from Sopping cart
  Background:
    Given User is on home page "https://islahiart.com/"
  @positive
  Scenario: xCheckout items from Shopping cart
     #Given User already open the website IslahiArt
    #When User username as "sarkernazmun@gmail.com" and password as "aBc123456!"
   # When User click on BestSelling link
   # And User click on item1 "SquareFrame" link into a cart
   # Then User navigating to BestSelling link
   # And User click on item2 "KidsTee" link into a cart
   # Then User should be able to see "SquareFrame" and "KidsTee" on Shopping Cart
   # When User click on Shopping cart button
   # Then User click on CHECKOUT button
   # Then User navigate to Billing details page
    When User input "naz" as First name "sar" as Last name
    Then User input "2801 E Whitestone Blvd" as House number and street name
    Then User input "Cedar Park" as City
    And User input "78613" as ZIP Code
    And User input "(512) 690-9340" as Phone
    And User input "xyz@gmail.com" as Email address
    Then User click on Klarna as payment option
    Then User click on terms and conditions
    Then User select "Texas" as State
    Then User verify the total price of the items
    Then User click on PLACE ORDER button
    Then User should see checkout complete message