@Login
Feature: LoginUp to IslahiArt Application

  Background:
    Given User is on IslahiArtLogIn page "https://islahiart.com/"

  @ValidCredentials
  Scenario: Login with valid credentials
    When User click on MyAccount icon
    When User enters username as "sarkernazmun@gmail.com" and password as "aBc123456!"
    Then User should be able to login successfully and new page open

 # @InvalidCredentials
 # Scenario Outline: Login with invalid credentials
 #   When User enters username as "<username>" and password as "<password>"
 #   Then User should be able to see error message "<errorMessage>"

 #   Examples:
 #     | username   | password  | errorMessage                      |
 #     | AbcXyz      | abc$$$$$ | Invalid credentials               |
     # | xyz@gmail   | admin123 | Invalid credentials               |
     # | abc123     | xyz$$     | Invalid credentials               |