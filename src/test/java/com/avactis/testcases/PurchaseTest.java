package com.avactis.testcases;

	import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avactis.base.BaseClass;
import com.avactis.pageobjects.IndexPage;
import com.avactis.utility.DButils;

import java.util.List;

	public class PurchaseTest extends BaseClass{

		 private IndexPage indexPage;
		
		 @BeforeMethod
		    public void setup() {
		        // Launch the application with the desired browser
		        launchApp(); // Fetch browser from config file

		        // Initialize IndexPage after launching the app
		        indexPage = new IndexPage(); // IndexPage should be initialized here
		    }

		    @AfterMethod
		    public void tearDown() {
		        // Quit the WebDriver after the test
		    	quitDriver();
		    }
		
	    @Test
	    public void purchaseProductsTest() throws Exception {
	        List<String[]> products = DButils.getProductData();
	        
	        for (String[] product : products) {
	            String category = product[0];
	            String name = product[1];
	            String price = product[2];
	            String quantity = product[3];

	            // Code to navigate to the product category and add the item to the cart
	            // For example:
	            // navigateToCategory(category);
	            // addToCart(name, Integer.parseInt(quantity));
	        }

	        // Proceed with checkout and verification steps
	    }
	}
	

