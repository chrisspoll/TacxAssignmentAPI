package com.tacx.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)//Run with Junit
@CucumberOptions(

        plugin ={"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt"},
        features = "src/test/resources",
        glue = "com/tacx/stepDefs",
        dryRun = false,
        strict = true,
        tags = "@smoke",
        publish = true


)

public class CukesRunner {
}
