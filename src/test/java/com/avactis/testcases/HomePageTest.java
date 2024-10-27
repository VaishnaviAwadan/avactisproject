package com.avactis.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avactis.base.BaseClass;
import com.avactis.pageobjects.HomePage;
import com.avactis.pageobjects.IndexPage;
import com.avactis.pageobjects.LoginPage;

public class HomePageTest  extends BaseClass{

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
	    public void mycartTest() throws Throwable{
	    	indexPage= new IndexPage();
	    	loginPage=indexPage.clickOnSignIn();
	    	homePage = loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
	    	boolean result=homePage.validateMyCart();
	    	 Assert.assertTrue(result);
	    }
}
