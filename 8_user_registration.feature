Feature: User Registration

  Scenario: User successfully registers with valid credentials
    Given the user is on the registration page
    When the user enters a valid email "testuser@example.com"
    And the user enters a valid password "StrongPass@123"
    And the user fills in all required registration details
    And the user clicks on the "Register" button
    Then the user should receive a confirmation email
    When the user clicks the confirmation link in the email
    Then the user account should be activated successfully
    And the user should be able to log in with email "testuser@example.com" and password "StrongPass@123"
