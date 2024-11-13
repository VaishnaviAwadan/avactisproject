package com.avactis.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.avactis.base.BaseClass;
import com.avactis.pageobjects.AddToCartPage;
import com.avactis.pageobjects.AddressPage;
import com.avactis.pageobjects.HomePage;
import com.avactis.pageobjects.IndexPage;
import com.avactis.pageobjects.LoginPage;
import com.avactis.pageobjects.OrderConfirmationPage;
import com.avactis.pageobjects.OrderPage;
import com.avactis.pageobjects.OrderSummary;
import com.avactis.pageobjects.PaymentPage;
import com.avactis.pageobjects.ShippingPage;
import com.avactis.pageobjects.SearchResultPage;

import scripts.ddt.dataProviders;

public class RegisteredUserTest extends BaseClass {
	
	 private IndexPage index;
	 private IndexPage indexPage;
	 private LoginPage loginPage;
	 private HomePage homePage;
	 private SearchResultPage searchResultPage;
	 private AddToCartPage addToCartPage;
	 private OrderPage orderPage;
	 private AddressPage addressPage;
	 private ShippingPage shippingPage;
	 private PaymentPage paymentPage;
	 private OrderSummary orderSummary;
	 private OrderConfirmationPage orderConfirmationPage;
	
	@BeforeMethod
    public void setup() {
        // Launch the application using the browser specified in the config file
        launchApp(); 
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver after the test
        driver.quit(); 
    }
    
    @Test(dataProvider = "dataFromCSV", dataProviderClass = dataProviders.class)
    public void RegisteredUser(String productName, String qty, String size) throws Throwable{
    	indexPage = new IndexPage();
    	searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity(qty);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddToCart();
		orderPage=addToCartPage.clickOnCheckOut();
		loginPage=orderPage.clickOnCheckOut();
		addressPage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"),addressPage);
		shippingPage=addressPage.clickOnCheckOut();
		//shippingPage.checkTheTerms();
		paymentPage=shippingPage.clickOnProceedToCheckOut();
		orderSummary=paymentPage.clickOnPaymentMethod();
		orderConfirmationPage=orderSummary.clickOnconfirmOrderBtn();
		String actualMessage=orderConfirmationPage.validateConfirmMessage();
		String expectedMsg="Your order on My Store is complete.";
		Assert.assertEquals(actualMessage, expectedMsg);
		Log.endTestCase("endToEndTest");
    	
    }
    }
	

