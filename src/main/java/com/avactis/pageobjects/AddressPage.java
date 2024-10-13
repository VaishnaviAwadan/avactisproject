package com.avactis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class AddressPage extends BaseClass {

	Action action = new Action();
	
	@FindBy(xpath ="//a[text()='Checkout']")
	private WebElement proceedToCheckOut;
	
	public AddressPage() {
		PageFactory.initElements(driver(), this);
		
	}
	
	public ShippingPage clickOnCheckOut() throws Throwable {
		Action.click(driver(), proceedToCheckOut );
		return new ShippingPage(driver());
	}
	
}
