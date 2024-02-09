package Steps;

import static org.testng.Assert.fail;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PageValidator_Steps {
	
	WebDriver driver;
	
	public PageValidator_Steps(Common_Steps common_steps) {
		this.driver = common_steps.getDriver();
	}

	
	@When("I click on {string}")
	public void i_click_on(String tabs) {
		driver.findElement(By.linkText(tabs)).click();
		
	}
	@Then("I validate that page navigates to {string} and title contains {string}")
	public void i_validate_that_page_navigates_to_and_title_contains(String url, String title) {
		String actUrl = driver.getCurrentUrl();
		String actTitle = driver.getTitle();
		if (!actUrl.equals(url)) {
				fail("Page doesn't navigate to the expected url");
			}
		if (!actTitle.contains(title)) {
			fail("Page doesn't have the expected title, the actual title is " + actTitle);
		}
	}
}
