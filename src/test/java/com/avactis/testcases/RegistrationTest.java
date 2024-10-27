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
   
}
