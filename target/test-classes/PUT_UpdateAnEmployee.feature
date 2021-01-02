Feature: PUT Update an employee

  Scenario Outline: Verify updated employee data
    Given User gets the ID from previously created employee
    When User sends PUT request  to "/update/" endpoint with
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    And User gets Content-Type "application/json"
 #   Then User gets status code 201
 #   And User gets "Updated" next to status code
    And User gets "success" value of status
    And User gets the given "<name>"
    And User gets the given salary "<salary>"
    And User gets the given age "<age>"
    And User gets "Successfully! Record has been updated." value of message
    Examples:
      | name       | salary | age |
      | updateName | 22222  | 55  |