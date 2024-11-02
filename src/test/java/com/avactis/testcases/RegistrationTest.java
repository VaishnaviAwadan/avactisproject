package com.avactis.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.avactis.base.BaseClass;
import com.avactis.pageobjects.AccountCreationPage;
import com.avactis.pageobjects.HomePage;
import com.avactis.pageobjects.IndexPage;
import com.avactis.pageobjects.LoginPage;

public class RegistrationTest extends BaseClass {

	private IndexPage indexPage;
	private LoginPage loginPage;
	private AccountCreationPage accountCreationPage;
	private HomePage homepage;

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
    	loginPage= indexPage.clickOnSignIn();
    	// Click on the "Register" button
    	homepage = new HomePage();
        accountCreationPage = homepage.clickOnRegister();
        Assert.assertNotNull(accountCreationPage, "Navigation to Account Creation page failed");
    	 }

    
    
 /* Define test data for registration
    String email = "vaishnavi88awadan@gmail.com";
    String pswd = "@Vaishnavi#93";
    String fName = "Vaishnavi";
    String lName = "Awadan";
    String country = "India";
    String state = "Maharastra";
    String zip = "416552";
    String city = "Kolhapur";
    String add1 = "Lane no 1";
    String add2 = "Shahu colony";
    String mobile = "8625897265";*/
    
 
    // Enter data in Account Creation form
    public void createAccount(String email, String pswd, String fName, String lName,
            String country, String state, String zip, String city,
            String add1, String add2, String mobile) throws Throwable {

    System.out.println("value entered");
    Thread.sleep(3000);
    // Submit the form and validate successful registration
    homepage = accountCreationPage.validateRegistration();
    Assert.assertNotNull(homepage, "Registration failed: HomePage was not reached after registration.");

    // Additional validation (Optional): Check if the user is redirected to the HomePage
    String currentUrl = homepage.getCurrURL();
    Assert.assertTrue(currentUrl.contains("home"), "User not redirected to home page after registration.");
}
}

