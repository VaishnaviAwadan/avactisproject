package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;

public class AddToCartPage {

Action action= new Action();
	
	@FindBy(name="quantity_in_cart")
	private WebElement quantity;
	
	@FindBy(name="po[18]")
	private WebElement size;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement addToCartBtn;
	
	@FindBy(xpath="//input[@value='Add To Wishlist']")
	private WebElement addToWishlist;
	
	
	
	public AddToCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

	public void enterQuantity(String quantity1) throws Throwable {
		action.type(quantity, quantity1);
	}
	
	public void selectSize(String size1) throws Throwable {
		action.selectByVisibleText(size1, size);
	}
	
	public void clickOnAddToCart() throws Throwable {
		action.click(driver, addToCartBtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		action.fluentWait(getDriver(), addToCartMessag, 10);
		return action.isDisplayed(getDriver(), addToCartMessag);
	}
	
	public OrderPage clickOnCheckOut() throws Throwable {
		action.fluentWait(getDriver(), proceedToCheckOutBtn, 10);
		action.JSClick(getDriver(), proceedToCheckOutBtn);
		return new OrderPage();
	}
	
}
