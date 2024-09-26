package com.avactis.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class LoginPage extends BaseClass{

	Action action = new Action();

    // Define the WebElements on the login page
    @FindBy(xpath = "//label[text()='E-Mail:']") // Email input field
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']") // Password input field
    private WebElement passwordInput;
  
    @FindBy(xpath = "//input[@value='Sign In']") // //input[@type='submit']
    private WebElement signInButton;    					
    
    @FindBy(xpath = "//a[text()='Forgot your Password?']") // Forgot password link //a[@href='http://localhost/Avactis/forgot-password.php']
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//input[@type='checkbox']") // "Keep me signed in" checkbox
    private WebElement keepMeSignedInCheckbox;

    // Constructor to initialize the page elements
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Method to log in by providing email and password
    public HomePage login(String email, String password) {
        action.type(emailInput, email);      // Enter the email address
        action.type(passwordInput, password); // Enter the password
        action.click(driver, signInButton);   // Click on the "Sign In" button

        return new HomePage();  // Assuming successful login leads to HomePage
    }

    // Method to check the "Keep me signed in" checkbox
    public void checkKeepMeSignedIn() {
        if (!keepMeSignedInCheckbox.isSelected()) {
            action.click(driver, keepMeSignedInCheckbox);
        }
    }

    // Method to click the "Forgot your password?" link
    public ForgotPasswordPage clickForgotPasswordLink() {
        action.click(driver, forgotPasswordLink);
        return new ForgotPasswordPage(); // Assuming it redirects to ForgotPasswordPage
    }
}
