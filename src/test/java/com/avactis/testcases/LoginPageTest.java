package com.avactis.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avactis.base.BaseClass;
import com.avactis.pageobjects.HomePage;
import com.avactis.pageobjects.IndexPage;
import com.avactis.pageobjects.LoginPage;

public class LoginPageTest extends BaseClass {

    private IndexPage indexPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setup() {
		// Launch the application using the browser specified in the config file
        launchApp(); // No need for String browser argument; it's handled by config
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver after the test
        driver.quit(); // Ensures proper driver shutdown
    }

    @Test
    public void loginTest() throws Throwable {
        // Initialize IndexPage
        indexPage = new IndexPage();
        
        // Click on the Sign In button to navigate to the LoginPage
        loginPage = indexPage.clickOnSignIn();
        
        // Wait for login page to load (optional sleep; use waitUtils for better handling)
        Thread.sleep(3000);
        
        // Perform login with credentials from the config file
        homePage = loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
        
        // Wait for home page to load (optional sleep; use proper waits in real tests)
        Thread.sleep(3000);
        
        // Validate that the current URL is the expected URL after login
        String actualUrl = homePage.getCurrURL();
        Thread.sleep(3000);
        String expectedUrl = "http://localhost/Avactis/index.php";
        Assert.assertEquals(actualUrl,expectedUrl, "Login failed or URL mismatch after login.");
    }
}
