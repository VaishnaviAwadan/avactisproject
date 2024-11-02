package com.avactis.testcases;
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
		//accountCreationPage = new AccountCreationPage();
        accountCreationPage = homePage.clickOnRegister();
       // Assert.assertNotNull(accountCreationPage, "Navigation to Account Creation page failed");
			homePage=accountCreationPage.registration
				(prop.getProperty("email"),
				prop.getProperty("pswd"),
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
		//Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
		

    
}
}
