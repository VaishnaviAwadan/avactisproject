package com.avactis.testcases;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avactis.base.BaseClass;
import com.avactis.pageobjects.AccountCreationPage;
import com.avactis.pageobjects.HomePage;
import com.avactis.pageobjects.IndexPage;

public class RegistrationPositiveTest extends BaseClass {

	private IndexPage indexPage;
	private AccountCreationPage accountCreationPage;
	private HomePage homePage;
	
	
	@BeforeMethod
    public void setup() {
        // Launch the application with the desired browser
        launchApp(); // Fetch browser from config file
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver after the test
        driver().quit();
    }
    
    @Test
    public void createRegistrationPositiveTest() throws Throwable {
    	indexPage= new IndexPage();
    	indexPage.clickOnSignIn();
    	// Click on the "Register" button
    	homePage = new HomePage();
        accountCreationPage = homePage.clickOnRegister();
        Assert.assertNotNull(accountCreationPage, "Navigation to Account Creation page failed");
    }

    @Test
    public void registrationTest() throws Throwable {
		indexPage= new IndexPage();
		indexPage.clickOnSignIn();
		homePage = new HomePage();
        accountCreationPage = homePage.clickOnRegister();
        
        System.out.println("emailID: " + prop.getProperty("email"));
        System.out.println("password: " + prop.getProperty("pswd"));
        System.out.println("rePassword: " + prop.getProperty("rPswd"));
        System.out.println("firstName: " + prop.getProperty("fName"));
        System.out.println("lastName: " + prop.getProperty("lName"));
        System.out.println("countryName: " + prop.getProperty("country"));
        System.out.println("stateName: " + prop.getProperty("state"));
        System.out.println("postCode: " + prop.getProperty("zip"));
        System.out.println("cityName: " + prop.getProperty("city"));
        System.out.println("address1: " + prop.getProperty("add1"));
        System.out.println("address2: " + prop.getProperty("add2"));
        System.out.println("mobileNo: " + prop.getProperty("mobile"));
        
			homePage=accountCreationPage.registration
				(prop.getProperty("email"),
				prop.getProperty("pswd"),
				prop.getProperty("rPswd"),
				prop.getProperty("fName"),
				prop.getProperty("lName"),
				prop.getProperty("country"),
				prop.getProperty("state"),
				prop.getProperty("zip"),
				prop.getProperty("city"),
				prop.getProperty("add1"),
				prop.getProperty("add2"),
				prop.getProperty("mobile")
    );
		
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			String actualUrl = homePage.getCurrURL();
	        System.out.println("Actual URL: " + actualUrl);
	        String expectedUrl = "http://localhost/Avactis/home.php";
			System.out.println("Register Successfully");
			Assert.assertTrue(actualUrl.contains(expectedUrl), "Login failed or URL mismatch after login.");
    
}
}
