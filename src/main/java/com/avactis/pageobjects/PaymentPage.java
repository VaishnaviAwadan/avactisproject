package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class PaymentPage extends BaseClass {

	private WebDriver driver;
	Action action= new Action();
	
	@FindBy(xpath = "//input[@name='paymentModule[method_code]']")
	private WebElement cashOndelivery;
	
	@FindBy(xpath = "//input[@value='BCE5D24D-666C-43CA-94A0-D6F775903BE2_3']")
	private WebElement deliveryOnTheNextBuisenessDay;
	
	@FindBy(xpath = "//input[@type='button' and @value='Continue Checkout']")
	private WebElement continueBtn;
	
	public PaymentPage() {
        this.driver = driver;
        PageFactory.initElements(driver, this);
}
	
	public OrderSummaryPage cashOndeliveryMethod() throws Throwable {
	Action.click(driver, cashOndelivery);
	return new OrderSummaryPage(driver);
	}
	
	public OrderSummary clickOnPaymentMethod() throws Throwable {
	    Action.click(driver(), continueBtn); // Assuming paymentMethodButton is correctly initialized
	    return new OrderSummary(); // Make sure this navigates to OrderSummary
	}
	
}
