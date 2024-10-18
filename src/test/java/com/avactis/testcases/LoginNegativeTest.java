package com.avactis.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avactis.base.BaseClass;
import com.avactis.pageobjects.HomePage;
import com.avactis.pageobjects.IndexPage;
import com.avactis.pageobjects.LoginPage;


public class LoginNegativeTest extends BaseClass {

	 private IndexPage indexPage;
	    private LoginPage loginPage;
	    private HomePage homePage;

	    @BeforeMethod
	    public void setup() {
	        // Launch the application using the browser specified in the config file
	        launchApp(); 
	    }

	    @AfterMethod
	    public void tearDown() {
	        // Quit the WebDriver after the test
	        driver.quit(); 
	    }
	
	    @Test
	    public void loginNegativeTest() throws Throwable {
	        // Initialize IndexPage
	        indexPage = new IndexPage();
	        
	        // Click on the Sign In button to navigate to the LoginPage
	        loginPage = indexPage.clickOnSignIn();
	        
	        // Wait for the login page to load
	        Thread.sleep(3000);
	        
	        // Print the credentials being used for debugging
	        System.out.println("Username: " + prop.getProperty("userName"));
	        System.out.println("Password: " + prop.getProperty("password"));
	        
	        // Perform login with valid credentials but simulate a failure (you could use incorrect credentials here)
	        homePage = loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
	        
	        // Wait for the home page (or error message) to load
	        Thread.sleep(3000);
	        
	        // Instead of checking for the home page URL, we check for an error message or failed login state
	        boolean loginFailed = loginPage.isLoginFailed(); // Assuming you have a method to check if login failed
	        Assert.assertTrue(loginFailed, "Login was expected to fail but it succeeded.");
	        
	        // Alternatively, you can validate an error message on the LoginPage (you may need to implement this method)
	        String errorMessage = loginPage.getErrorMessage(); 
	        String expectedErrorMessage = "Invalid credentials, please try again.";
	        Assert.assertEquals(errorMessage, expectedErrorMessage, "The error message does not match the expected result.");
	    }
	    
}
