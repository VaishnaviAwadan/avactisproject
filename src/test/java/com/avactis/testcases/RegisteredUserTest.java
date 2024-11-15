
package com.avactis.testcases;

import org.testng.Assert;
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
import com.avactis.pageobjects.ProductPage;
import com.avactis.pageobjects.ShippingPage;
import com.avactis.utility.Log;
import com.avactis.pageobjects.SearchResultPage;

import scripts.ddt.dataProviders;

public class RegisteredUserTest extends BaseClass {
	
	 //private IndexPage index;
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
    public void RegisteredUser(String productName, String qty, String price) throws Throwable{

    	HomePage homePage = new HomePage();
        homePage.navigateToStore();

        // Step 2: Add products to cart and verify
        ProductPage productPage = homePage.searchProduct(productName);
        productPage.addToCart(quantity);
        CartPage cartPage = productPage.goToCart();
        cartPage.verifyCartContents(productName, quantity, expectedPrice);

        // Step 3: CheckOut and verify address
        CheckOutPage checkOutPage = cartPage.proceedToCheckOut();
        checkOutPage.enterShippingAndBillingDetails("Sample Address Line 1", "City", "Country");
        checkOutPage.verifyAddressDetails("Sample Address Line 1");

        // Step 4: Select shipment and payment method, verify amount
        checkOutPage.selectShipmentMethod("Standard Shipping");
        checkOutPage.selectPaymentMethod("Credit Card");
        checkOutPage.verifyTotalAmount(expectedPrice);

        // Step 5: Verify final order confirmation
        OrderConfirmationPage orderConfirmationPage = checkOutPage.placeOrder();
        orderConfirmationPage.verifyOrderDetails(productName, quantity, expectedPrice);
        String orderID = orderConfirmationPage.getOrderID();
        
        // Step 6: Admin verification
        AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
        adminOrderPage.verifyOrderInAdmin(orderID, productName, quantity, expectedPrice);
    }
    	
    }
    
	
