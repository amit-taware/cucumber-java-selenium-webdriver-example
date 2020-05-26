package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/features/module2/DuckDuckGo.feature"},
        strict = false, plugin = {"pretty",
        "json:target/cucumber_json_reports/duckduckgo-page.json",
        "html:target/home-page-html"},
        glue = {"masterhooks",
                "steps"})
public class DuckDuckGoPageTest extends AbstractTestNGCucumberTests {
}
