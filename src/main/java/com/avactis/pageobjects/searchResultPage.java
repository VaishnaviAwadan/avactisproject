package com.avactis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class searchResultPage {

	public class SearchResultPage extends BaseClass {
		
		Action action= new Action();
		
		@FindBy(xpath="//*[@id=\"ProductForm_18\"]/div[2]/div[1]/img")
		private WebElement productResult;
		
		public SearchResultPage() {
			PageFactory.initElements(getDriver(), this);
		}
		
		public boolean isProductAvailable() throws Throwable {
			return action.isDisplayed(getDriver(), productResult);
		}
		
		public AddToCartPage clickOnProduct() throws Throwable {
			action.click(getDriver(), productResult);
			return new AddToCartPage();
		}
}
}
