package com.avactis.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.avactis.base.BaseClass;
import com.avactis.pageobjects.AccountCreationPage;
import com.avactis.pageobjects.HomePage;
import com.avactis.pageobjects.IndexPage;


public class RegistrationNegativeTest extends BaseClass {

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
    public void registrationTest() throws Throwable {
		indexPage= new IndexPage();
		indexPage.clickOnSignIn();
		homePage = new HomePage();
        accountCreationPage = homePage.clickOnRegister();

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
   
        String actualUrl = homePage.getCurrURL();
        System.out.println("Actual URL: " + actualUrl);
        String expectedUrl = "http://localhost/Avactis/register.php";
		System.out.println("Register failed");
		Assert.assertTrue(actualUrl.contains(expectedUrl), "Login failed or URL mismatch after login.");
        Thread.sleep(5000);
}
}

