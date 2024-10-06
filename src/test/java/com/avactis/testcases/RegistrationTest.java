package com.avactis.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.avactis.base.BaseClass;
import com.avactis.pageobjects.AccountCreationPage;
import com.avactis.pageobjects.HomePage;

public class RegistrationTest extends BaseClass {

    HomePage homePage;
    AccountCreationPage accountCreationPage;

    @BeforeMethod
    public void setup() {
        // Launch the application with the desired browser
        launchApp(prop.getProperty("browser")); // Fetch browser from config file
        
        // Initialize the HomePage after launching the app
        homePage = new HomePage(getDriver());
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver after the test
        getDriver().quit();
    }

    @Test
    public void registerPositiveTest() throws Throwable {
        // Step 1: Click on "My Account" link
        homePage.clickMyAccount();

        // Step 2: Click on "Register" button
        accountCreationPage = homePage.clickOnRegister();

        // Step 3: Enter valid registration data
        accountCreationPage.createAccount(
            "vaishnavi88awadan@gmail.com",  // Email
            "Vaishnavi#93",                // Password
            "Vaishnavi",                   // First Name
            "Awadan",                      // Last Name
            "India",                       // Country
            "Maharastra",                  // State
            "416552",                      // ZIP Code
            "Kolhapur",                    // City
            "Lane no 1",                   // Address Line 1
            "Shahu Colony",                // Address Line 2
            "8625897265"                   // Mobile Number
        );

        // Step 4: Submit the form and validate successful registration
        homePage = accountCreationPage.validateRegistration();

        // Step 5: Assert that registration was successful by verifying that "My Cart" is displayed
        Assert.assertTrue(homePage.isMyCartDisplayed(), "Registration failed! 'My Cart' element is not visible.");
        
        // Optional: Verify if the user is redirected to the correct page after registration
        String currentUrl = homePage.getCurrURL();
        Assert.assertTrue(currentUrl.contains("home"), "The user is not redirected to the home page after registration.");
    }
}
