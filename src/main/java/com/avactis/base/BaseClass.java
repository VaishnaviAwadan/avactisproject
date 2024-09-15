package com.avactis.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.avactis.pageobjects.LoginPage;
import com.avactis.utils.WaitUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	//public static WebDriver driver;
	public static Properties prop;
	public static WaitUtils waitUtils;
	
	
	// Constructor: Loads config and initializes WaitUtils
    public BaseClass() {
        loadConfig(); // Load properties from config file
        if (getDriver() != null) {
            waitUtils = new WaitUtils(getDriver()); 
        }
 // Initialize WaitUtils only if driver is not null
        }
	
	public void loadConfig()
	{
		try {
			prop = new Properties();
			System.out.println("Super constructor invoked");
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);
			System.out.println("driver:" +driver);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Launch browser based on the provided browser name
	
	public void launchApp(String browserName) {
		// String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
		}
		 waitUtils = new WaitUtils(getDriver()); // Reinitialize WaitUtils after setting the driver
}
	
	// Method to get WebDriver instance from ThreadLocal
    public static WebDriver getDriver() {
        return driver.get();
    }
	
	 // Click method with explicit wait
    public void click(By locator) {
		WebElement element = waitUtils.waitForElementToBeVisible(locator);
        element.click();
    }

    // SendKeys method with explicit wait
    public void sendKeys(By locator, String text) {
		WebElement element = waitUtils.waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    // GetText method with explicit wait
    public String getText(By locator) {
        WebElement element = waitUtils.waitForElementToBeVisible(locator);
        return element.getText();
    }

    // isElementDisplayed method with explicit wait
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitUtils.waitForElementToBeVisible(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
     // Method to quit WebDriver instance
        public static void quitDriver() {
            getDriver().quit();
            driver.remove(); // Remove the WebDriver instance to avoid memory leak
    }
}