package com.avactis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;
import java.time.Duration;

public class LoginPage extends BaseClass {

  

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

    // Constructor to initialize the page elements
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    
    // Login method for returning to HomePage
    public HomePage login(String uname, String pswd)throws Throwable {
            Action.type(userName, uname);
            Action.type(password, pswd);
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
}
