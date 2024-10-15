package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class ShippingPage extends BaseClass {

    WebDriver driver;
    Action action = new Action();

    // WebElements on the Shipping Page
    @FindBy(xpath = "//input[@class='input_checkbox']")
    private WebElement shippingAddressSameAs;

    @FindBy(xpath = "//input[@name='full_tax_exempt_status']")
    private WebElement taxExemptNumber;

    @FindBy(xpath = "//input[@name='subscriptionTopics[Topics][1]']")
    private WebElement specialOffer;

    @FindBy(xpath = "//input[@name='subscriptionTopics[Topics][2]']")
    private WebElement newProducts;

    @FindBy(xpath = "//input[@onclick='submitStep(1);']")
    private WebElement continueCheckout;

    // Constructor to initialize the page elements
    public ShippingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver(), this);
    }

    // Method to check the "Shipping Address Same As" checkbox
    public void checkShippingAddressSameAs() throws Throwable {
        Action.click(driver(), shippingAddressSameAs);
    }

    public void ChecktaxExemptNumber() throws Throwable{
    	Action.click(driver(),taxExemptNumber);
    }
    
    public void CheckSpecialOffer() throws Throwable{
    	Action.click(driver(), specialOffer);
    }
    
    public void CheckNewProduct() throws Throwable{
    	Action.click(driver(),newProducts);
    }
    // Method to click on "Continue Checkout" and navigate to the PaymentPage
    public PaymentPage clickOnContinueCheckout() throws Throwable {
        Action.click(driver(), continueCheckout);
        return new PaymentPage(driver()); // Initialize PaymentPage with WebDriver
    }
}
