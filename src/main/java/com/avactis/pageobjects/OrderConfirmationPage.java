package com.avactis.pageobjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avactis.base.BaseClass;

public class OrderConfirmationPage extends BaseClass {

	@FindBy(xpath = "//div[@class='note note-success note-bordered' and contains(text(), 'Your order is placed.')]") // Use the correct locator here
	WebElement confirmationMessageElement;

	public String validateConfirmMessage() {
	    return confirmationMessageElement.getText();
}
}
