package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

	public class OrderSummaryPage extends BaseClass {
		
		
		@FindBy(xpath="//input[@value='Place Order']")
		private WebElement placeorderbtn;
		
		public OrderSummaryPage(WebDriver driver) {
			PageFactory.initElements(driver(), this);
		}

		public OrderConfirmationPage clickOnconfirmOrderBtn() throws Throwable {
			Action.click(driver(), placeorderbtn);
			return new OrderConfirmationPage();
		}
		
	}
	

