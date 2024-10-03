package com.avactis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class OrderPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(xpath="//*[@id=\"Product_Quan\"]/table/tbody/tr[2]/td[4]/strong")
	private WebElement unitPrice;
	
	@FindBy(xpath= "//*[@id=\"Product_Quan\"]/table/tbody/tr[2]/td[5]/strong")
	private WebElement totalPrice;
	
	@FindBy(xpath="//a[text()='Checkout']")
	private WebElement proceedToCheckOut;
	
	public OrderPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public double getUnitPrice() {
		String unitPrice1=unitPrice.getText();
		//String unit=unitPrice1.replaceAll("[^0-9.]", "");
		String unit=unitPrice1.replaceAll("[^a-zA-Z0-9]","");
		double finalUnitPrice=Double.parseDouble(unit);
		return finalUnitPrice/100;
	}
	
	public double getTotalPrice() {
		String totalPrice1=totalPrice.getText();
		// Allow decimal points in price
		//String tot=totalPrice1.replaceAll("[^0-9.]", "");
		String tot=totalPrice1.replaceAll("[^a-zA-Z0-9]","");
		double finalTotalPrice=Double.parseDouble(tot);
		return finalTotalPrice/100;
	}
	
	public LoginPage clickOnCheckOut() throws Throwable {
		action.click(driver, proceedToCheckOut);
		return new LoginPage(getDriver());
	}
}
