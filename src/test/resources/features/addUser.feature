@addUser
Feature:

  Background:
		#@SYS-259
    Given Librarian is on the add user page

  @SYS-258 @SYS-266
  Scenario: Verify that librarian can add user
    When Librarian enters valid inputs to the options fields
    Then Librarian should be able to click save changes button
    And Librarian should be able to see "The user has been created." message


  @SYS-260 @SYS-266
  Scenario: Verify add user options

    Then librarian should see following add user options
      | Full Name  |
      | Password   |
      | Email      |
      | User Group |
      | Status     |
      | Start Date |
      | End Date   |
      | Address    |


  @SYS-261 @SYS-266
  Scenario: verify user types on add user page
    When Librarian click user type dropdown
    Then Librarian should see following user group options
      | Librarian |
      | Students  |


  @SYS-262 @SYS-266
  Scenario: Verify status on add user page
    When Librarian click on status dropdown
    Then Librarian should see following status options
      | ACTIVE   |
      | INACTIVE |

  @SYS-263 @SYS-266
  Scenario Outline: Verify end date should not be before start date
    When Librarian enter start date "<startDate>" and end date "<endDate>"
    And Librarian enter valid options to other fields
    Then Librarian should not be able to save new user

    Examples:
      | startDate  | endDate    |
      | 22-04-2022 | 22-03-2022 |
      | 15-06-2021 | 15-05-2021 |
      | 10-12-2021 | 10-10-2021 |

  @SYS-264 @SYS-266
  Scenario Outline: Verify full name, password, email options should not be empty on add user page
    When Librarian make "<options>" empty
    And Librarian put valid inputs to other fields except "<options>"
    Then Librarian should see "<options>" menu "This field is required." error message
    And Librarian should not be able to save new user

    Examples:
      | options   |
      | full_name |
      | password  |
      | email     |

  @SYS-265 @SYS-266
  Scenario: Verify valid email is entered
    When Librarian enter invalid email
    Then Librarian should be able to see "Please enter a valid email address." error message
    And Librarian should not be able to save new user

  Scenario: Verify
