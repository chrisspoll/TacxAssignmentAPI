Feature: DELETE An Employee
  Scenario: Verify the deleted employees data
    Given User gets the ID from previously created employee
    When User sends DELETE request to "/delete/" endpoint with the id
    Then User gets status code 200
    And User gets Content-Type "application/json"
    And User gets "OK" next to status code
    And User gets "success" value of status
    And User gets data number as given id
    And User gets "Successfully! Record has been deleted" value of message