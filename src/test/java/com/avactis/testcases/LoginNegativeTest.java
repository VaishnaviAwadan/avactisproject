package com.avactis.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avactis.base.BaseClass;
import com.avactis.pageobjects.IndexPage;
import com.avactis.pageobjects.LoginPage;


public class LoginNegativeTest extends BaseClass {

	 private IndexPage indexPage;
	    private LoginPage loginPage;
	   // private HomePage homePage;

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
	        indexPage = new IndexPage();
	        loginPage = indexPage.clickOnSignIn();
	        
	        loginPage.login("invalidUser", "invalidPass");
	      
;	        
	        // Check if login failed
	        Assert.assertTrue(loginPage.isLoginFailed(), "Account and password could not be identified. Try again or create an account.");
	        
	        // Verify the error message
	        String actualErrorMessage = loginPage.getErrorMessage();
	        String expectedErrorMessage = "Account and password could not be identified. Try again or create an account.";
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");
	        System.out.println("Login Failed");
	        // Instead of checking for the home page URL, we check for an error message or failed login state
	        boolean loginFailed = loginPage.isLoginFailed(); // Assuming you have a method to check if login failed
	        Assert.assertTrue(loginFailed, "Login was expected to fail but it succeeded.");
	        
	    }
	    
}
