package Steps;

import static org.testng.Assert.fail;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderProducts_Steps {
	
	WebDriver driver;
	
	public OrderProducts_Steps(Common_Steps common_steps) {
		this.driver = common_steps.getDriver();
	}
	
	
	@Given("I want to order {string} {string}")
	public void i_want_to_order(String count, String products) throws InterruptedException {
//		String userName = "iman.tester1@gmail.com";
//		String password = "Tosca123!";
		//Log in
		WebElement logIn = driver.findElement(By.xpath("//a[normalize-space()='Log in']"));
		if (logIn.isDisplayed()) {
			logIn.click();
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(100));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Email")));
			driver.findElement(By.id("Email")).sendKeys("iman.tester1@gmail.com");
			driver.findElement(By.id("Password")).sendKeys("Tosca123!");
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
			if (!driver.findElement(By.xpath("//img[@alt='Tricentis Demo Web Shop']")).isDisplayed()) {
				fail("You are not in the Home Page after login");
			}
		}
		
		//Directing to the Blue Jeans product page
		driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Apparel & Shoes']")).click();
		driver.findElement(By.xpath("//h2[@class='product-title']//a[normalize-space()='Blue Jeans']")).click();
		String title = driver.findElement(By.xpath("//h1[normalize-space()='Blue Jeans']")).getText();

		if(!title.equals(products)) {
			fail("Name of the product doesn't match!");
		}
		
		//Adding quantity of products
		//driver.findElement(By.xpath("//input[@id='addtocart_36_EnteredQuantity']")).clear();
		driver.findElement(By.xpath("//input[@id='addtocart_36_EnteredQuantity']")).sendKeys(count);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button-36']")).click();
		
		//Bar notification validation
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[text()='The maximum quantity allowed for purchase is 100.']")));
		String errorText= driver.findElement(By.xpath("//p[text()='The maximum quantity allowed for purchase is 100.']")).getText();
		if(errorText.equals("The maximum quantity allowed for purchase is 100.")) {
			driver.findElement(By.xpath("//span[@title='Close']")).click();
			driver.findElement(By.xpath("//input[@id='addtocart_36_EnteredQuantity']")).clear();
			driver.findElement(By.xpath("//input[@id='addtocart_36_EnteredQuantity']")).sendKeys(count);
			driver.findElement(By.xpath("//input[@id='add-to-cart-button-36']")).click();
			
		} 
		//Error handling
		else {
			System.out.println("Pop Up doesn't display");
			fail("Pop Up doesn't display");
		}
		
	}
	@When("I complete purchase")
	public void i_complete_purchase() {
		//Click on the Shopping cart link
		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
		
		// Select country from the drop down list
		WebElement countryDropdown = driver.findElement(By.xpath("//select[@id='CountryId']"));
		Select selectCountry = new Select(countryDropdown);
		selectCountry.selectByVisibleText("United Kingdom");
		
		// Check the check box if unchecked and continue
		WebElement termsCheckbox = driver.findElement(By.id("termsofservice"));
		if (!termsCheckbox.isSelected()) {
		    termsCheckbox.click();
		}
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		//Continue Billing address
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@onclick='Billing.save()']")));
		WebElement addressDropdown = driver.findElement(By.id("billing-address-select"));
		Select selectAddress = new Select(addressDropdown);
		selectAddress.selectByVisibleText("Iman Tester1, Vienna street, Vienna 1234, Austria");		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@onclick='Billing.save()']")));
		driver.findElement(By.xpath("//input[@onclick='Billing.save()']")).click();
		
		// Select in store check box and continue 
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("PickUpInStore")));
		WebElement pickupCheckbox = driver.findElement(By.id("PickUpInStore"));
		if(!pickupCheckbox.isSelected()) {
			pickupCheckbox.click();
		}
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@onclick='Shipping.save()']")));
		driver.findElement(By.xpath("//input[@onclick='Shipping.save()']")).click();
		
		//Click on cash method
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@id='paymentmethod_0']")));
		driver.findElement(By.xpath("//input[@id='paymentmethod_0']")).click();
		
		//Continue "Cash On Delivery" payment method
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']")).click();
		
		//Continue payment information
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//p[normalize-space()='You will pay by COD'])[1]")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
		
		//Confirm Order
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='cart-footer']")));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@value='Confirm']")));
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();

	}
	    

	@Then("I verify the success message")
	public void i_verify_the_success_message() {
		//Confirming the success message and continue
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='section order-completed']")));
		String orderMessage = driver.findElement(By.xpath("//div[@class='title']")).getText();
		System.out.println(orderMessage);
		if(!orderMessage.equals("Your order has been successfully processed!")) {
			fail("Your order has NOT been successfully processed!");
		}
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		//Confirming we directed to the home page
		if (!driver.findElement(By.xpath("//img[@alt='Tricentis Demo Web Shop']")).isDisplayed()) {
			fail("You are not in the Home Page after order confirmation");
		}
	}
}
