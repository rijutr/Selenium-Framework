package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",
        glue = "step_definitions", tags = "@ErrorValidation",
monochrome = true, plugin = {"html:target/cucumber.html"})
public class ErrorValidationCucumberTest extends AbstractTestNGCucumberTests {
}
