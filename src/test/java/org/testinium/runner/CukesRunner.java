package org.testinium.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = "org/testinium/step_defs",
        dryRun = false,
        plugin = "html:target/cucumber-report.html",
        publish = false,
        tags = ""
)
public class CukesRunner {
}
