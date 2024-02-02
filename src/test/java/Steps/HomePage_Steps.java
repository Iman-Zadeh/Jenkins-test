package Steps;


import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePage_Steps {
	
	WebDriver driver;
	
	public HomePage_Steps (Common_Steps common_steps) {
		this.driver = common_steps.getDriver();
	}
	
	@Given("I am on the Home Page")
	public void i_am_on_the_home_page() {
		driver.get("https://demowebshop.tricentis.com/");
		
		//Log in
		WebElement logIn = driver.findElement(By.xpath("//a[normalize-space()='Log in']"));
		if (logIn.isDisplayed()) {
			logIn.click();
		}
	}

	@When("I Click on the APPAREL & SHOES tab")
	public void i_click_on_the_apparel_shoes_tab() {
	    driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Apparel & Shoes']")).click();
	    
	}

	@Then("I navigate to the Apparel & Shoes page")
	public void i_navigate_to_the_apparel_shoes_page() {
	    String expUrl = "https://demowebshop.tricentis.com/apparel-shoes";
	    String actUrl = driver.getCurrentUrl();
	    if (!expUrl.equals(actUrl)) {
	    	fail("Page doen't navigate to the expected page");
	    }
	}
	
	@When("I Click on the login link")
	public void i_click_on_the_login_link() {
	    driver.findElement(By.xpath("//a[normalize-space()='Log in']")).click();
	}

	@Then("I navigate to the login page")
	public void i_navigate_to_the_login_page() {
	    String expUrl = "https://demowebshop.tricentis.com/login";
	    String actUrl = driver.getCurrentUrl();
	    if (!expUrl.equals(actUrl)) {
	    	fail("Page doen't navigate to the expected page");
	    }
	}

	@When("I Click on the BOOKS tab")
	public void i_click_on_the_books_tab() {
	    driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Books']")).click();
	}
	@Then("I navigate to the Books page")
	public void i_navigate_to_the_books_page() {
	    String expUrl = "https://demowebshop.tricentis.com/booksss";
	    String actUrl = driver.getCurrentUrl();
	    if (!expUrl.equals(actUrl)) {
	    	fail("Page doen't navigate to the expected page"); 
	    }
	}
}
