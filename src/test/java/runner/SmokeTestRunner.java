package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/smoke",
        glue = "steps",
        tags = "@smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/smoke.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class SmokeTestRunner {
}
