package com.avactis.actiondriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionClass {

    private WebDriver driver;
    private Actions actions;

    // Constructor to initialize WebDriver and Actions
    public ActionClass(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    // Method to hover over an element
    public void hoverOverElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            actions.moveToElement(element).perform();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element not found to hover: " + locator);
        }
    }

    // Method to perform right-click on an element
    public void rightClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            actions.contextClick(element).perform();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element not found to right click: " + locator);
        }
    }

    // Method to double-click on an element
    public void doubleClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            actions.doubleClick(element).perform();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element not found to double click: " + locator);
        }
    }

    // Method to drag and drop from one element to another
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        try {
            WebElement sourceElement = driver.findElement(sourceLocator);
            WebElement targetElement = driver.findElement(targetLocator);
            actions.dragAndDrop(sourceElement, targetElement).perform();
        } catch (StaleElementReferenceException e) {
            System.out.println("Drag and Drop failed, element not found: " + sourceLocator + " or " + targetLocator);
        }
    }

    // Method to click and hold an element
    public void clickAndHold(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            actions.clickAndHold(element).perform();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element not found to click and hold: " + locator);
        }
    }
}