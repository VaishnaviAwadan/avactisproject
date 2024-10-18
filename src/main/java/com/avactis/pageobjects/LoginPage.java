package com.avactis.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;
public class LoginPage extends BaseClass {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
    // Define the WebElements on the login page
    @FindBy(xpath = "//label[text()='E-Mail:']//following::input[1]")  // Email input field
    private WebElement userName;

    @FindBy(xpath = "//input[@type='password']") // Password input field
    private WebElement password;

    @FindBy(xpath = "//input[@value='Sign In']") // Sign in button
    private WebElement signInButton;

    @FindBy(xpath = "//a[text()='Forgot your Password?']") // Forgot password link
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//input[@type='checkbox']") // "Keep me signed in" checkbox
    private WebElement keepMeSignedInCheckbox;

    @FindBy(xpath = "//input[@id='new-email']")  // New email input for account creation
    private WebElement emailForNewAccount;

    @FindBy(xpath = "//button[@id='create-account']")  // Create new account button
    private WebElement createNewAccountBtn;

    @FindBy(xpath = "//div[@class='note note-danger' and contains(text(), 'Account and password could not be identified')]")  // Error message for failed login (adjust XPath as per your app)
    private WebElement loginErrorMessage;
    
    // Constructor to initialize the page elements
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    
    // Login method for returning to HomePage
    public HomePage login(String uname, String pswd)throws Throwable {
    	    userName.clear();
            Action.type(userName, uname);
            password.clear();
            Action.type(password, pswd);
            wait.until(ExpectedConditions.visibilityOf(signInButton));
            Action.click(driver, signInButton);
            return new HomePage();
    }

    // Login method with AddressPage return
    public AddressPage login(String uname, String pswd, AddressPage addressPage) {
            Action.scrollByVisibilityOfElement(driver, userName);
            Action.type(userName, uname);
            Action.type(password, pswd);
            Action.click(driver, signInButton);
            return new AddressPage();  // Initialize AddressPage with driver
    }

    // Method to create a new account
    public AccountCreationPage createNewAccount(String newEmail) {
            Action.type(emailForNewAccount, newEmail);
            Action.click(driver, createNewAccountBtn);
            return new AccountCreationPage();  // Initialize AccountCreationPage with driver
        
}
    // Method to check if login failed based on the presence of an error message
    public boolean isLoginFailed() {
        try {
            // Wait for the error message to be visible if login fails
			wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
            // If the error message is displayed, return true indicating login failure
            return loginErrorMessage.isDisplayed();
        } catch (Exception e) {
            // If no error message is found, login did not fail
            return false;
        }
}

	
		  // Method to retrieve the error message text when login fails
	    public String getErrorMessage() {
	        try {
				// Wait for the error message to be visible
	            wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
	            // Return the text of the error message
	            return loginErrorMessage.getText();
	        } catch (Exception e) {
	            // If no error message is found, return a default message
	            return "No error message displayed.";
	        }
	    }
	}
