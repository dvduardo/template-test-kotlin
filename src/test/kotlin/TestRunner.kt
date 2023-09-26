import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith


@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/features"],
    plugin = ["pretty", "json:target/cucumber-reports/Report.json"],
    glue = ["step_definition"]
)
class TestRunner