package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;


public class AccountCreationPage extends BaseClass {
	
	WebDriver driver;
	Action action= new Action();
	
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

	@FindBy(name = "customer_info[Customer][Country]")
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

	@FindBy(xpath="//input[@type='submit']")
	private WebElement registerBtn;
	
	public AccountCreationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(getDriver(), this);
	}
	
	public void createAccount(
			String email, 
			String pswd, 
			String fName, 
			String lName, 
			String country,
			String state, 
			String zip, 
			String City, 
			String add1, 
			String add2, 
			String mobile) throws Throwable {
		
		
		action.type(emailID, email);
		action.type(password, pswd);
		action.type(firstName, fName);
		action.type(lastName, lName);

		Select countryDropdown = new Select(countryName);
		countryDropdown.selectByVisibleText(country);

		Select stateDropdown = new Select(stateName);
		stateDropdown.selectByVisibleText(state);

		
		action.type(postCode, zip);
		action.type(cityName, City);
		action.type(address1,add1 );
		action.type(address2,add2);
	    action.type(mobileNo, mobile);
	}
	
	public HomePage validateRegistration() throws Throwable {
		registerBtn.click();
		return new HomePage(getDriver());
	}
	
	public boolean validateAcountCreatePage() throws Throwable {
		 return action.isDisplayed(getDriver(), formTitle);
	}
	
}



/*package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;
import org.apache.log4j.Logger;

public class AccountCreationPage extends BaseClass {

    private static final Logger log = Logger.getLogger(AccountCreationPage.class);

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "email_address")
    private WebElement emailField;

    @FindBy(id = "agree_terms")
    private WebElement agreeTermsCheckbox;

    @FindBy(xpath = "//*[@id='error_message']")
    private WebElement errorMsg;

    Action action = new Action();

    // Constructor
    public AccountCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }

    public void setFirstName(String firstName) {
        log.info("Setting first name: " + firstName);
        fillTextField(firstNameField, firstName);
    }

    public void setLastName(String lastName) {
        log.info("Setting last name: " + lastName);
        fillTextField(lastNameField, lastName);
    }

    public void setEmail(String email) {
        log.info("Setting email: " + email);
        if (!email.contains("@")) {
            log.error("Invalid email format: " + email);
            throw new IllegalArgumentException("Invalid email format");
        }
        fillTextField(emailField, email);
    }

    public void clickAgreeTerms() {
        log.info("Clicking on Agree Terms checkbox");
        action.click(agreeTermsCheckbox);
        Assert.assertTrue(agreeTermsCheckbox.isSelected(), "Agree terms checkbox is not selected.");
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement element = waitUtils.waitForElementToBeVisible(errorMsg);
            log.info("Error message displayed: " + element.getText());
            return element.isDisplayed();
        } catch (Exception e) {
            log.warn("Error message not found.");
            return false;
        }
    }

    // Reusable method for filling text fields
    private void fillTextField(WebElement element, String text) {
        waitUtils.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }
}
*/