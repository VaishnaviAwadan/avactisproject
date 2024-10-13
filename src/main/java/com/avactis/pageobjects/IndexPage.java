
package com.avactis.pageobjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class IndexPage extends BaseClass {

	@FindBy(xpath = "//a[text()='Sign In']") 
	private WebElement signInBtn;
	
	@FindBy(xpath = "//a[@class='site-logo']")
	private WebElement avactisLogo;
	
	@FindBy(xpath = "//input[@type='text' and @name='search_pattern' and @class='form-control input_text']")
	private WebElement searchProductBox;
	
	@FindBy(xpath = "//button[@type='submit' and text()='Search']")
	private WebElement searchButton;
	
	// Modified constructor to accept WebDriver as a parameter
	public IndexPage() {
		PageFactory.initElements(driver, this); // Use the passed driver instance
	}
	 
	public LoginPage clickOnSignIn() throws Throwable {
		Action.click(driver, signInBtn);
		return new LoginPage();
	}
	
	public boolean validateLogo() throws Throwable {
		return Action.isDisplayed(driver, avactisLogo);
	}
	
	public String getAvactisTitle() {
		String avactisTitle = driver.getTitle();
		return avactisTitle;
	}
	
	public SearchResultPage searchProduct(String productName) throws Throwable {
		Action.type(searchProductBox, productName);
		Action.scrollByVisibilityOfElement(driver(), searchButton);
		Action.click(driver, searchButton);
		Thread.sleep(3000);
		return new SearchResultPage();
	}
}
