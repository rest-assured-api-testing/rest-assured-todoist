import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import entities.Project;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Projects {

    @BeforeMethod(onlyForGroups = "createProject")
    public void createProjects() {
        //create project
    }

    @Test
    public void getAllProjectTest() {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setToken("5703275f22fce7ac417a198be65764263fd0bf6b");
        apiRequest.setBaseUri("https://api.todoist.com/rest/v1");
        apiRequest.setEndpoint("/projects");
        apiRequest.setMethod(ApiMethod.GET);

        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "createProject")
    public void getProjectTest() {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setToken("5703275f22fce7ac417a198be65764263fd0bf6b");
        apiRequest.setBaseUri("https://api.todoist.com/rest/v1");
        apiRequest.setEndpoint("/projects/{projectId}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("projectId", "2212086923");

        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Project project = apiResponse.getBody(Project.class);
        System.out.println("---"+project.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getColor(), 48);
        apiResponse.validateBodySchema("schemas/project.json");
    }
}
