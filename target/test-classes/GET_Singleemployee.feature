Feature: GET Single Employee Data

  Scenario Outline: Verify employee's data
    When User sends GET request to "/employee/<numberOfEmployee>" endpoint
    Then User gets status code 200
    And User gets Content-Type "application/json"
    And User gets "success" value of status
    And User gets employee id as <id>
    And User gets employee name as "<name>"
    And User gets employee salary as <salary>
    And User gets employee age as <age>
    Examples:
      | numberOfEmployee | id | name        | salary | age |
      | 1                | 1  | Tiger Nixon | 320800 | 61  |