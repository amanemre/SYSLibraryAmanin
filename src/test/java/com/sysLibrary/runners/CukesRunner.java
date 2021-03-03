package com.sysLibrary.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                  "html:target/defaults-html-reports"},
        features = "src/test/resources/features",
        glue = "com/sysLibrary/step_definitions",
        dryRun = false,
        //strict = true,
        tags = "@addUser"
)
public class CukesRunner {

}
