package rest.todoist.org.hooks;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Project;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;


public class ScenariosHooks {
    private Logger log = Logger.getLogger(getClass());

    private ApiRequest apiRequest;
    private ApiResponse apiResponse;

    public ScenariosHooks(ApiRequest apiRequest, ApiResponse apiResponse){
        //LOGGER.info("ScenariosHooks constructor");
        this.apiRequest = apiRequest;
        this.apiResponse = apiResponse;
    }

    @Before(value = "@CreateProject or @DeleteProject", order = 1)
    public void setUp() {
        log.info("***Before SetUp***");
         String userToken = "5703275f22fce7ac417a198be65764263fd0bf6b";
         String baseUri = "https://api.todoist.com/rest/v1";
        apiRequest.setBaseUri(baseUri);
        apiRequest.setToken(userToken);
    }

    @Before(value = "@CreateProject", order = 2)
    public void createProject() throws JsonProcessingException {
        log.info("****Before createProject****");
        System.out.println("--****Before createProject****");
        Project project = new Project();
        project.setName("Task List25");
        apiRequest.setEndpoint("projects");
        apiRequest.setMethod(ApiMethod.POST);
        apiRequest.setBody(new ObjectMapper().writeValueAsString(project));
        ApiManager.executeWithBody(apiRequest, apiResponse);
        apiResponse.getResponse().then().log().all();
    }

    @After(value = "@CreateProject or @DeleteProject")
    public void deleteProject(){
        log.info("****Before deleteProject****");
        apiRequest.setEndpoint("projects/{projectId}");
        apiRequest.clear();
        apiRequest.addPathParam("projectId",apiResponse.getBody(Project.class).getId().toString());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiManager.execute(apiRequest, apiResponse);
    }
}
