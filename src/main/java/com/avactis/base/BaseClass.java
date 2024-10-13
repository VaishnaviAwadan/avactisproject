
package com.avactis.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.avactis.utils.WaitUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    protected static WebDriver driver; // Change to protected for subclass access
    public static Properties prop; // Removed static for instance-level use
    public WaitUtils waitUtils;
    public JavascriptExecutor jsExecutor; // Declare JavascriptExecutor

    // Constructor: Loads config, initializes WaitUtils, and sets JavascriptExecutor
    public BaseClass() {
        loadConfig(); // Load properties from config file
    }

    // Method to load the configuration file
    public void loadConfig() {
        try {
            prop = new Properties();
            System.out.println("Loading configuration...");
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + File.separator + "Configuration" + File.separator + "config.properties");
            prop.load(ip);
            System.out.println("Configuration loaded.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Config file not found. Please check the path.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading config file.");
        } 
    }

    // Method to launch browser and application
    public static void launchApp() {
    	WebDriverManager.chromedriver().setup();
    	String browserName=prop.getProperty("browser");
    	if(browserName.contains("Chrome")) {
    		driver=new ChromeDriver();
    	}
    	else if(browserName.contains("FireFox")){
    		driver=new FirefoxDriver();
    	}
    	else if(browserName.contains("IE")){
    		driver=new InternetExplorerDriver();
    	}	
        		
        // Maximize window and delete cookies
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

       // Action.implicitwait(driver, 10);
       // Action.pageLoadTimeOut(driver, 30);
        
        // Launch the URL from the config file
        driver.get(prop.getProperty("url"));

     
    }

    
    
    // Method to get the WebDriver instance
    public WebDriver driver() {
        return driver;
    }

    // Click method with explicit wait and JavaScript fallback
    public void click(By locator) {
        try {
            WebElement element = waitUtils.waitForElementToBeVisible(locator);
            element.click();
        } catch (Exception e) {
            // Use JavascriptExecutor if normal click fails
            WebElement element = waitUtils.waitForElementToBeVisible(locator);
            jsExecutor.executeScript("arguments[0].click();", element);
        }
    }

    // SendKeys method with explicit wait and JavaScript fallback
    public void sendKeys(By locator, String text) {
        try {
            WebElement element = waitUtils.waitForElementToBeVisible(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            // Use JavascriptExecutor if sendKeys fails
            WebElement element = waitUtils.waitForElementToBeVisible(locator);
            jsExecutor.executeScript("arguments[0].value='" + text + "';", element);
        }
    }

    // GetText method with explicit wait
    public String getText(By locator) {
        WebElement element = waitUtils.waitForElementToBeVisible(locator);
        return element.getText();
    }

    // isElementDisplayed method with explicit wait and JavaScript check
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitUtils.waitForElementToBeVisible(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;  // Element not found or not visible within the timeout
        }
    }

    // Scroll to element using JavaScript
    public void scrollToElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Method to quit WebDriver instance
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Set driver to null to avoid memory leak
        }
    }

	public boolean mouseover(WebDriver driver, WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}
}
