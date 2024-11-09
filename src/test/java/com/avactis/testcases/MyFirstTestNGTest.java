package com.avactis.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class MyFirstTestNGTest {
	WebDriver driver;
  @Test
  public void loginToSTCToursimWebsite() throws InterruptedException {
	// driver = new FirefoxDriver();
	  //  driver.manage().window().maximize();
	     
	     //driver = new EdgeDriver();
	     
	    // driver = new SafariDriver();
	     driver.get("https://nichethyself.com/tourism/home.html");
		  
	    //2. Type username as "stc123"
	    WebElement user = driver.findElement(By.name("username"));
	     user.sendKeys("stc123");
	     WebElement password = driver.findElement(By.name("password"));
	     password.sendKeys("12345");
	     
	    //driver.findElement(By.name("password")).sendKeys("12345");
		
	     user.submit();
	     //driver.findElemet
	     Thread.sleep(3000);
	     
	     String expectedTitle = "My account";
	     String actualTitle = driver.getTitle();
	     
	    // if(expectedTitle.equals(actualTitle)) {
	    //	 System.out.println("Test Passed");
	    // }else {
	    //	 System.out.println("Test Failed");
	    // }
	     assertEquals(actualTitle,expectedTitle);
  }
  @BeforeMethod
  public void beforeMethod() {
	  //Webdriver is an interface.
		String name;
		int age;
		
  driver = new ChromeDriver();
  driver.manage().window().maximize();
  }
@Test
  
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

 // @BeforeClass
 // public void beforeClass() {
  //}

 // @AfterClass
 // public void afterClass() {
 // }

}
