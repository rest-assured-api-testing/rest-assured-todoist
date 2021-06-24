package rest.todoist.org.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

//@CucumberOptions(
// glue = {"rest.todoist.org"}  ,
// features = {"src/test/resources/features"}
// )
//@CucumberOptions
public class RunTests extends AbstractTestNGCucumberTests {
    @BeforeTest
    public void beforeExecution(){
        System.out.println("-------------beforeExecution------------------");
    }

    @AfterTest
    public void afterExecution(){
        System.out.println("-----------afterExecution-------------");
    }
}

