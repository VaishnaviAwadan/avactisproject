package com.avactis.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.avactis.actiondriver.Action;
import com.avactis.base.BaseClass;

public class HomePage extends BaseClass {

    // Web Elements using @FindBy annotations

    @FindBy(xpath = "//a[text()='My Account']")
    private WebElement myAccount;
    
    @FindBy(xpath = "//a[text()='My cart']")
    private WebElement myCart;

    @FindBy(xpath = "//a[text()='Checkout']")
    private WebElement checkout;
    
    @FindBy(xpath = "//a[@href='http://localhost/Avactis/register.php']") // Assuming "Register" button has an ID or appropriate locator
    private WebElement registerButton;

    public HomePage() {
        PageFactory.initElements(driver(), this);
    }
 

   // Navigate to "My Account" page using JavaScript
    public boolean validateMyAccount() throws Throwable {
       return Action.isDisplayed(driver, myAccount);
    }
    
   // Validate if "My Cart" is displayed
    public boolean isMyCartDisplayed() {
        return isElementDisplayed(By.xpath("//a[text()='My cart']"));
    }
    
    // Navigate to "Checkout" page using JavaScript
    public void clickCheckout() {
        clickElementWithJS(checkout);
    }

 // Method to click on the register button
    public AccountCreationPage clickOnRegister() {
        registerButton.click();
        return new AccountCreationPage(); // Ensure you return an instance of AccountCreationPage
    }

    
    // Get the current URL of the page
    public String getCurrURL() {
        return driver().getCurrentUrl();
    }

    // Utility methods using JavascriptExecutor

    // Click element using JavaScript
    private void clickElementWithJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    // Scroll to element using JavaScript
    public void scrollToElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Set text using JavaScript
    public void setTextWithJS(WebElement element, String text) {
        jsExecutor.executeScript("arguments[0].value='" + text + "';", element);
    }
}