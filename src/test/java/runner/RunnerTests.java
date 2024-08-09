package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = " ", features = {"src/test/resources/features/Login.feature",
       "src/test/resources/features/ShoppingCart.feature",
       "src/test/resources/features/xCheckout.feature"},
        glue = {"step_definitions"},
        plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/"} )
public class RunnerTests extends AbstractTestNGCucumberTests {
}
