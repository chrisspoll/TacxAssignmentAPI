package com.tacx.stepDefs;

import com.tacx.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class GETAllEmployeeData {

    String baseUrl = ConfigurationReader.get("BaseUrl");
    Response response;

    @When("User sends GET request to {string} endpoint")
    public void user_sends_get_request_to_endpoint(String path) {
        response = RestAssured.given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.get("BaseUrl") + path);
    }

    @Then("User gets status code {int}")
    public void user_gets_status_code(Integer statusCode) {
        Assert.assertEquals("Verify Status Code 200",(int)statusCode,response.statusCode());
    }

    @Then("User gets {string} next to status code")
    public void user_gets_next_to_status_code(String expectedStr) {
        Assert.assertTrue("Verify String next to status code",response.getStatusLine().contains(expectedStr));
    }

    @Then("User gets Content-Type {string}")
    public void user_gets_content_type(String expContentType) {
        Assert.assertEquals("Verify response content type",expContentType,response.contentType());
    }

    @And("User gets {string} value of status")
    public void userGetsValueOfStatus(String value) {
        Assert.assertEquals("Verify status value",value,response.path("status"));
    }

    @Then("User gets {int}. employee id as {int}")
    public void user_gets_employee_id_as(Integer numberOfEmployee, Integer expectedId) {
        Integer actualId = Integer.parseInt(response.path("data[" + (numberOfEmployee-1) + "].id"));
        Assert.assertEquals("Verify employee id",expectedId,actualId);

    }

    @Then("User gets {int}. employee name as {string}")
    public void user_gets_employee_name_as(Integer numberOfEmployee, String expectedName) {
        String actualName = response.path("data[" + (numberOfEmployee-1) + "].employee_name");
        Assert.assertEquals("Verify employee name",expectedName,actualName);

    }

    @Then("User gets {int}. employee salary as {int}")
    public void user_gets_employee_salary_as(Integer numberOfEmployee, Integer expectedSalary) {
        Integer actualSalary = Integer.parseInt(response.path("data[" + (numberOfEmployee-1) + "].employee_salary"));
        Assert.assertEquals("Verify employee salary",expectedSalary,actualSalary);


    }

    @Then("User gets {int}. employee age as {int}")
    public void user_gets_employee_age_as(Integer numberOfEmployee, Integer expectedAge) {
        Integer actualAge = Integer.parseInt(response.path("data[" + (numberOfEmployee-1) + "].employee_age"));
        Assert.assertEquals("Verify employee age",expectedAge,actualAge);
    }

    @And("User gets employee id as {int}")
    public void userGetsEmployeeIdAsId(Integer expectedId) {
         Integer actualId = response.path("data.id");
         Assert.assertEquals("Verify id",expectedId,actualId);
    }

    @And("User gets employee name as {string}")
    public void userGetsEmployeeNameAs(String expectedName) {
        String actualName = response.path("data.employee_name");
        Assert.assertEquals("Verify employee name",expectedName,actualName);
    }

    @And("User gets employee salary as {int}")
    public void userGetsEmployeeSalaryAsSalary(Integer expectedSalary) {
        Integer actualSalary = response.path("data.employee_salary");
        Assert.assertEquals("Verify employee salary",expectedSalary,actualSalary);
    }

    @And("User gets employee age as {int}")
    public void userGetsEmployeeAgeAsAge(Integer expectedAge) {
        Integer actualAge = response.path("data.employee_age");
        Assert.assertEquals("Verify employee age",expectedAge,actualAge);
    }

    @When("User sends POST request to {string} endpoint with {string} {int} {int}")
    public void userSendsPOSTRequestToEndpointWithSalaryAge(String path, String givenName, Integer givenSalary, Integer givenAge) {
        String requestBody = "{\n" +
                "    \"name\":\"" + givenName + "\",\n" +
                "    \"salary\":\"" + givenSalary + "\",\n" +
                "    \"age\":\"" + givenAge + "\"\n" +
                "}";
        response = RestAssured.given().accept(ContentType.JSON)
                .when().body(requestBody)
                .post(ConfigurationReader.get("BaseUrl") + path);

    }

    @And("User gets the given {string}")
    public void userGetsTheGiven(String givenName) {
        String actualName = response.path("data.name");
        Assert.assertEquals("Verify name is the given name",givenName,actualName);
    }

    @And("User gets the given {int}")
    public void userGetsTheGivenSalary(Integer salary) {
    }

    @And("User gets {string} value of message")
    public void userGetsValueOfMessage(String arg0) {
    }

    @And("User saves the new employee id")
    public void userSavesTheNewEmployeeId() {
    }
}
