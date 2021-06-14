import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Projects {

    @Test
    public void getAllProjectTest() {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setToken("<PUT YOUR TOKEN HERE>");
        apiRequest.setBaseUri("https://api.todoist.com/rest/v1");
        apiRequest.setEndpoint("/projects");
        apiRequest.setMethod(ApiMethod.GET);

        Response response = ApiManager.execute(apiRequest);
        response.then().assertThat().statusCode(200);
    }
}
