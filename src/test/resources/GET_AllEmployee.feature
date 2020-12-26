Feature: GET All Employee Data

  Scenario Outline: Verify first employee data
    When User sends GET request to "/employees" endpoint
    Then User gets status code 200
    And User gets "OK" next to status code
    And User gets Content-Type "application/json;charset=utf-8"
    And User gets "success" value of status
    And User gets <numberOfEmployee>. employee id as <id>
    And User gets <numberOfEmployee>. employee name as "<name>"
    And User gets <numberOfEmployee>. employee salary as <salary>
    And User gets <numberOfEmployee>. employee age as <age>

    Examples:
      | numberOfEmployee | id | name        | salary | age |
      | 1                | 1  | Tiger Nixon | 320800 | 61  |


