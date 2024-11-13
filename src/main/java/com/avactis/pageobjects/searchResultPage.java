package com.avactis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;


	public class SearchResultPage extends BaseClass {
		
		
		@FindBy(xpath="//*[@id=\"ProductForm_18\"]/div[2]/div[1]/img")
		private WebElement productResult;
		
		public SearchResultPage() {
			PageFactory.initElements(driver(), this);
		}
		
		public boolean isProductAvailable() throws Throwable {
			return Action.isDisplayed(driver(), productResult);
		}
		
		public AddToCartPage clickOnProduct() throws Throwable {
			Action.click(driver(), productResult);
			return new AddToCartPage();
		}
}
