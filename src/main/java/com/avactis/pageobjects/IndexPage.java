package com.avactis.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class IndexPage extends BaseClass {
	
	Action action = new Action();
	
	@FindBy(xpath = "//a[text()='Sign In']") 
	private WebElement signInBtn;
	
	@FindBy(xpath = "//a[@class='site-logo']")
	private WebElement avactisLogo;
	
	@FindBy(xpath = "//input[@type='text' and @name='search_pattern' and @class='form-control input_text']")
	private WebElement searchProductBox;
	
	@FindBy(xpath = "//button[@type='submit' and text()='Search']")
	private WebElement searchButton;
	
	// Modified constructor to accept WebDriver as a parameter
	public IndexPage(WebDriver driver) {
		PageFactory.initElements(driver, this); // Use the passed driver instance
	}
	
	public LoginPage clickOnSignIn() throws Throwable {
		action.fluentWait(getDriver(), signInBtn, 10);
		action.click(getDriver(), signInBtn);
		return new LoginPage(getDriver());
	}
	
	public boolean validateLogo() throws Throwable {
		// Use explicit wait
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(avactisLogo)); // Wait until logo is visible
		return avactisLogo.isDisplayed();
	}
	
	public String getAvactisTitle() {
		String avactisTitle = getDriver().getTitle();
		return avactisTitle;
	}
	
	public SearchResultPage searchProduct(String productName) throws Throwable {
		action.type(searchProductBox, productName);
		action.scrollByVisibilityOfElement(getDriver(), searchButton);
		action.click(getDriver(), searchButton);
		Thread.sleep(3000);
		return new SearchResultPage();
	}
}
