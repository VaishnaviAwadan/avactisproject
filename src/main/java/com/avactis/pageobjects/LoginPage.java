package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class LoginPage extends BaseClass {

    Action action = new Action();

    // Define the WebElements on the login page
    @FindBy(xpath = "//label[text()='E-Mail:']//following::input[1]")  // Email input field
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']") // Password input field
    private WebElement passwordInput;

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
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Login method for returning to HomePage
    public HomePage login(String uname, String pswd) throws Throwable {
        action.scrollByVisibilityOfElement(getDriver(), emailInput);
        action.type(emailInput, uname);
        action.type(passwordInput, pswd);
        action.JSClick(getDriver(), signInButton);
        Thread.sleep(2000);
        return new HomePage(getDriver());  // Initialize HomePage with driver
    }

    // Login method with AddressPage return
    public AddressPage login(String uname, String pswd, AddressPage addressPage) throws Throwable {
        action.scrollByVisibilityOfElement(getDriver(), emailInput);
        action.type(emailInput, uname);
        action.type(passwordInput, pswd);
        action.click(getDriver(), signInButton);
        Thread.sleep(2000);
        return new AddressPage();  // Initialize AddressPage with driver
    }

    // Method to create a new account
    public AccountCreationPage createNewAccount(String newEmail) throws Throwable {
        action.type(emailForNewAccount, newEmail);
        action.click(getDriver(), createNewAccountBtn);
        return new AccountCreationPage(getDriver());  // Initialize AccountCreationPage with driver
    }
}
