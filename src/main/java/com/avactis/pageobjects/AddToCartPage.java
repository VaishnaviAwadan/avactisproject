package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class AddToCartPage extends BaseClass {

Action action= new Action();
	
	@FindBy(name="quantity_in_cart")
	private WebElement quantity;
	
	@FindBy(name="po[18]")
	private WebElement size;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement addToCartBtn;
	
	@FindBy(xpath="//input[@value='Add To Wishlist']")
	private WebElement addToWishlist;
	
	
	
	public AddToCartPage() {
        PageFactory.initElements(driver, this);
    }

	public void enterQuantity(String quantity1) throws Throwable {
		Action.type(quantity, quantity1);
	}
	
	public void selectSize(String size1) throws Throwable {
		action.selectByVisibleText(size1, size);
	}
	
	public void clickOnAddToCart() throws Throwable {
		Action.click(driver, addToCartBtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		action.fluentWait(driver(), addToCartMessag, 10);
		return action.isDisplayed(driver(), addToCartMessag);
	}
	
	public OrderPage clickOnCheckOut() throws Throwable {
		action.fluentWait(driver(), proceedToCheckOutBtn, 10);
		action.JSClick(driver(), proceedToCheckOutBtn);
		return new OrderPage();
	}
	
}
