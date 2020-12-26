Feature: POST Create a new employee
  @wip
  Scenario Outline: Verify created employee data
    When User sends POST request to "/create" endpoint with "<name>" <salary> <age>
    Then User gets status code 201
    And User gets Content-Type "application/json"
    And User gets "Created" next to status code
    And User gets "success" value of status
    And User gets the given "<name>"
    And User gets the given <salary>
    And User gets the given <age>
    And User gets "added" value of message
    And User saves the new employee id
    Examples:
      | name    | salary | age |
      | newName | 11000  | 44  |
