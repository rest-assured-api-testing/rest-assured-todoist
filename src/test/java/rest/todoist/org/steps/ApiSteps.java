package rest.todoist.org.steps;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Project;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class ApiSteps {
    private Logger log = Logger.getLogger(getClass());
    private ApiRequest apiRequest;
    private ApiResponse apiResponse;

public ApiSteps(ApiRequest apiRequest, ApiResponse apiResponse){
   // LOGGER.info("ApiSteps constructor");
    this.apiRequest = apiRequest;
    this.apiResponse = apiResponse;
}

    @Given("^I build \"(GET|DELETE)\" request$")
    public void iBuildRequest(String method) {
        log.info("I build the request");
        apiRequest.setMethod(ApiMethod.valueOf(method));
    }

    @Given("^I build \"(POST|PUT)\" request$")
    public void iBuildPayloadRequest(String method, DataTable jsonData) throws JsonProcessingException {
        log.info("I build the request");
        String body = new ObjectMapper().writeValueAsString(jsonData.asMap(String.class,String.class));
        apiRequest.setMethod(ApiMethod.valueOf(method));
        apiRequest.setBody(body);
    }

    @When("^I execute \"(.*?)\" request$")
    public void iExecuteRequest(String endpoint) {
        log.info("I execute the request");
        Project project = apiResponse.getBody(Project.class);
        apiRequest.setEndpoint(endpoint);
        apiRequest.addPathParam("projectId", project.getId().toString());
        ApiManager.execute(apiRequest, apiResponse);
        apiResponse.getResponse().then().log().body();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("^I execute \"(.*?)\" request with body$")
    public void iExecuteRequestWithRequest(String endpoint) {
        log.info("I execute the request");
        apiRequest.setEndpoint(endpoint);
        ApiManager.executeWithBody(apiRequest, apiResponse);
        apiResponse.getResponse().then().log().body();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        log.info("I verify the response");
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        apiResponse.getResponse().then().log().body();
    }
}
