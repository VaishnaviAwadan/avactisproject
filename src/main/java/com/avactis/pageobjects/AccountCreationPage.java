package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class AccountCreationPage extends BaseClass {

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    @FindBy(xpath = "//h3[text()='Create new account']")
    private WebElement formTitle;

    @FindBy(name = "customer_info[Customer][Email]")
    private WebElement emailID;

    @FindBy(name = "customer_info[Customer][Password]")
    private WebElement password;

    @FindBy(name = "customer_info[Customer][FirstName]")
    private WebElement firstName;

    @FindBy(name = "customer_info[Customer][LastName]")
    private WebElement lastName;

    @FindBy(xpath = "//select[@name='customer_info[Customer][State]']")
    private WebElement countryName;

    @FindBy(name = "customer_info[Customer][State]")
    private WebElement stateName;

    @FindBy(name = "customer_info[Customer][ZipCode]")
    private WebElement postCode;

    @FindBy(name = "customer_info[Customer][City]")
    private WebElement cityName;

    @FindBy(name = "customer_info[Customer][Streetline1]")
    private WebElement address1;

    @FindBy(name = "customer_info[Customer][Streetline2]")
    private WebElement address2;

    @FindBy(name = "customer_info[Customer][Phone]")
    private WebElement mobileNo;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement registerBtn;

    // Constructor to initialize elements and driver
    public AccountCreationPage() {
        PageFactory.initElements(driver, this);
    }

    /* Method to create a new account
    public void createAccount(
        String email,
        String pswd,
        String fName,
        String lName,
        String country,
        String state,
        String zip,
        String city,
        String add1,
        String add2,
        String mobile
    ) throws Throwable {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for elements to be visible and interact with them
        wait.until(ExpectedConditions.visibilityOf(emailID)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pswd);
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fName);
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lName);

        // Select country from dropdown
        Select countryDropdown = new Select(wait.until(ExpectedConditions.visibilityOf(countryName)));
        countryDropdown.selectByVisibleText(country);

        // Select state from dropdown
        Select stateDropdown = new Select(wait.until(ExpectedConditions.visibilityOf(stateName)));
        stateDropdown.selectByVisibleText(state);

        // Fill in other details
        wait.until(ExpectedConditions.visibilityOf(postCode)).sendKeys(zip);
        wait.until(ExpectedConditions.visibilityOf(cityName)).sendKeys(city);
        wait.until(ExpectedConditions.visibilityOf(address1)).sendKeys(add1);
        wait.until(ExpectedConditions.visibilityOf(address2)).sendKeys(add2);
        wait.until(ExpectedConditions.visibilityOf(mobileNo)).sendKeys(mobile);
    }*/

 // Login method for returning to HomePage
    public HomePage registration(String email, String pswd, String fName, String lName, String country, String state, String zip, String city, String add1, String add2, String mobile)throws Throwable {
    	   // userName.clear();
    	    emailID.clear();
            Action.type(emailID, email);
            wait.until(ExpectedConditions.visibilityOf(emailID));
            Thread.sleep(3000);
           // password.clear();
            Action.type(password, pswd);
            Action.type(firstName, fName);
            Action.type(lastName, lName);
            
            // Select dropdown options
            Select countryDropdown = new Select(wait.until(ExpectedConditions.visibilityOf(countryName)));
            countryDropdown.selectByVisibleText(country);

            Select stateDropdown = new Select(wait.until(ExpectedConditions.visibilityOf(stateName)));
            stateDropdown.selectByVisibleText(state);

            // Populate remaining fields
            Action.type(postCode, zip);
            Action.type(cityName, city);
            Action.type(address1, add1);
            Action.type(address2, add2);
            Action.type(mobileNo, mobile);
            
            wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
            
            return new HomePage();
    }
    
    // Method to submit the form and validate registration
    public HomePage validateRegistration() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
        return new HomePage();
    }

    // Method to verify if the account creation page is displayed
    public boolean validateAccountCreatePage() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(formTitle)).isDisplayed();
    }
}
