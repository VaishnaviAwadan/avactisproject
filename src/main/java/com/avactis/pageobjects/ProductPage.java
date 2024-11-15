package com.avactis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    private WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locator for the product search bar
    @FindBy(xpath = "//input[@type='text' and @name='search_pattern']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit' and text()='Search']")
    private WebElement searchButton;

    // Locator for the add to cart button on the product details page
    @FindBy(xpath = "//button[contains(@class, 'add-to-cart') and text()='Add to Cart']") // Update locator if needed
    private WebElement addToCartBtn;

    // Locator for a specific product in the search results
    @FindBy(xpath = "//h3[contains(text(), 'Custom T-Shirt')]") // Update this locator with dynamic product selection if needed
    private WebElement productNameLocator;

    // Method to search for a product by name
    public void searchProduct(String productName) {
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    // Method to select a product from search results
    public void selectProduct(String productName) {
        if (productNameLocator.getText().equalsIgnoreCase(productName)) {
            productNameLocator.click();
        }
    }

    // Method to add the product to the cart
    public void addToCart() {
        addToCartBtn.click();
    }
}
