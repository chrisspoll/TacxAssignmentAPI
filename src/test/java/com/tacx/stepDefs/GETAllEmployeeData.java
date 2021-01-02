package com.tacx.stepDefs;

import com.tacx.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class GETAllEmployeeData {

    static String id;
    static String name;
    static String salary;
    static String age;
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

    @When("User sends POST request to {string} endpoint with")
    public void userSendsPOSTRequestToEndpointWith(String path, Map<String,Object> employee) {
        response = RestAssured.given().contentType(ContentType.JSON)
                .and().body(employee)
                .when().post(baseUrl + path);
        response.prettyPrint();

    }
    @When("User sends POST request to {string} endpoint with {string} {int} {int}")
    public void userSendsPOSTRequestToEndpointWithSalaryAge(String path, String givenName, Integer givenSalary, Integer givenAge) {
        String requestBody = "{\n" +
                "    \"name\":\"" + givenName + "\",\n" +
                "    \"salary\":\"" + givenSalary + "\",\n" +
                "    \"age\":\"" + givenAge + "\"\n" +
                "}";

        response = RestAssured.given().contentType(ContentType.JSON)
                .and().body(requestBody)
                .when().post(ConfigurationReader.get("BaseUrl") + path);

        System.out.println("response.path(\"data.name\") = " + response.path("data.name"));
        System.out.println("response.path(\"data.salary\") = " + response.path("data.salary"));

    }

    @And("User gets the given {string}")
    public void userGetsTheGiven(String givenName) {
        String actualName = response.path("data.name");
        Assert.assertEquals("Verify name is the given name",givenName,actualName);
    }

    @And("User gets the given salary {string}")
    public void userGetsTheGivenSalarySalary(String givenSalary) {
        String actualSalary = response.path("data.salary");
        Assert.assertEquals("Verify salary is the given salary",givenSalary,actualSalary);
    }

    @And("User gets the given age {string}")
    public void userGetsTheGivenAgeAge(String givenAge) {
        String actualAge = response.path("data.age");
        Assert.assertEquals("Verify age is the given age",givenAge,actualAge);
    }

    @And("User gets {string} value of message")
    public void userGetsValueOfMessage(String word) {
        Assert.assertTrue("Verify the message contains the given word",response.path("message").toString().contains(word));
    }

    @And("User saves the new employee id")
    public void userSavesTheNewEmployeeId() {
        id = Integer.toString(response.path("data.id"));
    }


    @Given("User gets the ID from previously created employee")
    public void userGetsTheIDFromPreviouslyCreatedEmployee() {

    }

    @When("User sends PUT request  to {string} endpoint with")
    public void userSendsPUTRequestToEndpointWith(String path,Map<String,Object> employee) {
        response = RestAssured.given().contentType(ContentType.JSON)
                .and().body(employee)
                .when().put(baseUrl + path + id);
        response.prettyPrint();
    }

    @When("User sends DELETE request to {string} endpoint with the id")
    public void userSendsDELETERequestToEndpointWithTheId(String path) {
        response = RestAssured.given().contentType(ContentType.JSON)
                .when().delete(baseUrl + path + id);
        response.prettyPrint();
    }

    @And("User gets data number as given id")
    public void userGetsDataNumberAsGivenId() {
        Assert.assertEquals("Verify deleted id is previously created id",id,response.path("data"));
    }
}
