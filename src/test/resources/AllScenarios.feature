@smoke
Feature: Tacx Collection

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

  Scenario Outline: Verify created employee data
    When User sends POST request to "/create" endpoint with
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
#    Then User gets status code 201
    And User gets Content-Type "application/json"
 #   And User gets "Created" next to status code
    And User gets "success" value of status
    And User gets the given "<name>"
    And User gets the given salary "<salary>"
    And User gets the given age "<age>"
    And User gets "added" value of message
    And User saves the new employee id
    Examples:
      | name    | salary | age |
      | newName | 11000  | 44  |

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

  Scenario: Verify the deleted employees data
    Given User gets the ID from previously created employee
    When User sends DELETE request to "/delete/" endpoint with the id
    Then User gets status code 200
    And User gets Content-Type "application/json"
    And User gets "OK" next to status code
    And User gets "success" value of status
    And User gets data number as given id
    And User gets "Successfully! Record has been deleted" value of message