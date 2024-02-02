package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"C:\\Windows\\System32\\config\\systemprofile\\eclipse-workspace\\Demo_Project\\features"},
		glue = {"Steps"},
		plugin = {"pretty"},
		//dryRun = false,
		monochrome = false,
		tags = "@tag2.1"
		)



public class test extends AbstractTestNGCucumberTests {
	
}
