package steps;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.AddBankAndCashPage;
import pages.LoginPage;
import pages.TestBase;

public class LoginStepDefinition extends TestBase {

	LoginPage loginPage;
	AddBankAndCashPage bankandCashpage;

	@Before
	public void beforeRun() {
		initDriver();
		loginPage= PageFactory.initElements(driver, LoginPage.class);	
		bankandCashpage= PageFactory.initElements(driver, AddBankAndCashPage.class);
		
	}

	@Given("User is on the techfios login page")
	public void user_is_on_the_techfios_login_page() {
		driver.get("https://techfios.com/billing/?ng=admin/");

	}

	@When("User enters the {string} in the {string} field")
	public void user_enters_the_in_the_field(String logindata, String logindataField) {
	
		
		
		switch (logindataField) {
		
		case "username":
			
			loginPage.enterUserName(logindata);
		
			break;
			
		case "password":
			loginPage.enterPassword(logindata);
			
			break;
		}			

		
//		if (logindataField.equals("username")) {
//			
//						
//		loginPage.enterUserName(logindata);
//				
//		}else if (logindataField.equals("password")) {
//		
//			loginPage.enterPassword(logindata);
//		
//			
//		} else {
//			System.out.println("incorrect username or password" );
//		}
			
	}

	@When("User clicks on {string}")
	public void user_clicks_on(String clickOn) {
		
		
		switch (clickOn) {
		
		case "login":
			loginPage.clickSignInButton();
			break;
		case "bankCash":
		bankandCashpage.clickBankAndCash();
		break;
		
		case "newAccount":
		bankandCashpage.clickNewAccount();
		break;
		case "submit":
			bankandCashpage.clickSubmitButton();
			break;
		
		
		}
		
		

	}

	@Then("User should land on Dashboard page")
	public void user_should_land_on_Dashboard_page() {
		String expedtedDashboardHeader= "Dashboard";
		String ActualDashboardHeader= loginPage.getPageHeader();
		Assert.assertEquals("Dashborad page not found", expedtedDashboardHeader, ActualDashboardHeader);
		screenshot(driver);

	}

	@Then("User enters {string} in the {string} field in accounts page")
	public void user_enters_in_the_field_in_accounts_page(String customerData, String customerDataField) {

		switch (customerDataField) {
		
		case "accountTitle":
			bankandCashpage.insertAccountTitle(customerData);
			break;
		case "description":
			bankandCashpage.insertDescription(customerData);
			break;
		case "initialBalance":
			bankandCashpage.insertInitialBalance(customerData);
			break;
		case "accountNumber":
			bankandCashpage.insertAccauntNumber(customerData);
			break;
		case "contactPerson":
			bankandCashpage.insertContactPerson(customerData);
			break;
		case "Phone":
			bankandCashpage.insertPhoneNumber(customerData);
			break;
		case "internetBankingURL":
			bankandCashpage.insertInternetBankingUrl(customerData);
			break;
		
		
		}
		
	}

	@Then("User should be able to validate account created successfully")
	public void user_should_be_able_to_validate_account_created_successfully() {
		String expectedPagerHeader ="Manage Accounts";
		String ActualPageHeader= bankandCashpage.validationPage();				
		Assert.assertEquals("Account added successfully", expectedPagerHeader, ActualPageHeader);
		screenshot(driver);

	}
	

	//@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
