package com.avactis.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.avactis.utils.WaitUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;
    public static WaitUtils waitUtils;
    
    // Constructor: Loads config and initializes WaitUtils
    public BaseClass() {
        loadConfig(); // Load properties from config file
        if (driver != null) {
            waitUtils = new WaitUtils(driver); // Initialize WaitUtils only if driver is not null
        }
    }
    
    // Method to load the configuration file
    public void loadConfig() {
        try {
            prop = new Properties();
            System.out.println("Super constructor invoked");
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\Configuration\\config.properties");
            prop.load(ip);
            System.out.println("driver: " + driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to launch browser and application
    public void launchApp(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }

        // Maximize window and delete cookies
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Launch the URL from the config file
        driver.get(prop.getProperty("url"));
        
        waitUtils = new WaitUtils(driver); // Reinitialize WaitUtils after setting the driver
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
        driver.quit();
        driver = null; // Set driver to null to avoid memory leak
    }
}
