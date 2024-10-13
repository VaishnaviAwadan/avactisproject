package com.avactis.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.avactis.base.BaseClass;

public class Action extends BaseClass {

    // Scroll to the element's visibility
    public static void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);
    }

    // Click using Actions
    public static void click(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).click().build().perform();
    }

    // Find element and return status
    public static boolean findElement(WebDriver driver, WebElement ele) {
        try {
            return ele.isDisplayed();
        } catch (Exception e) {
            System.out.println("Unable to locate element.");
            return false;
        }
    }

    // Check if element is displayed
    public static boolean isDisplayed(WebDriver driver, WebElement ele) {
        boolean isFound = findElement(driver, ele);
        if (isFound) {
            System.out.println("Element is " + (ele.isDisplayed() ? "displayed" : "not displayed"));
            return ele.isDisplayed();
        }
        return false;
    }

    // Check if element is selected
    public boolean isSelected(WebDriver driver, WebElement ele) {
        if (findElement(driver, ele)) {
            return ele.isSelected();
        }
        return false;
    }

    // Check if element is enabled
    public boolean isEnabled(WebDriver driver, WebElement ele) {
        if (findElement(driver, ele)) {
            return ele.isEnabled();
        }
        return false;
    }

    // Type in a text field
    public static boolean type(WebElement ele, String text) {
        try {
            ele.clear();
            ele.sendKeys(text);
            System.out.println("Successfully entered value");
            return true;
        } catch (Exception e) {
            System.out.println("Unable to enter value");
            return false;
        }
    }

    // Select by send keys
    public boolean selectBySendkeys(String value, WebElement ele) {
        try {
            ele.sendKeys(value);
            System.out.println("Select value from the DropDown");
            return true;
        } catch (Exception e) {
            System.out.println("Not Selected value from the DropDown");
            return false;
        }
    }

    // Select by index
    public boolean selectByIndex(WebElement element, int index) {
        try {
            new Select(element).selectByIndex(index);
            System.out.println("Option selected by Index");
            return true;
        } catch (Exception e) {
            System.out.println("Option not selected by Index");
            return false;
        }
    }

    // Select by visible text
    public boolean selectByVisibleText(String visibletext, WebElement ele) {
        try {
            new Select(ele).selectByVisibleText(visibletext);
            System.out.println("Option selected by VisibleText");
            return true;
        } catch (Exception e) {
            System.out.println("Option not selected by VisibleText");
            return false;
        }
    }

    // JavaScript click
    public boolean JSClick(WebDriver driver, WebElement ele) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
            System.out.println("Click Action is performed");
            return true;
        } catch (Exception e) {
            System.out.println("Click Action is not performed");
            return false;
        }
    }

    // Switch to frame by index
    public boolean switchToFrameByIndex(WebDriver driver, int index) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
            System.out.println("Frame with index \"" + index + "\" is selected");
            return true;
        } catch (Exception e) {
            System.out.println("Frame with index \"" + index + "\" is not selected");
            return false;
        }
    }

    // Switch to default frame
    public void switchToDefaultFrame(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    // Perform mouse over using Actions
    public void mouseOverElement(WebDriver driver, WebElement element) {
        try {
            new Actions(driver).moveToElement(element).perform();
            System.out.println("MouseOver Action is performed");
        } catch (Exception e) {
            System.out.println("MouseOver action is not performed");
        }
    }

    // Drag and drop by offset
    public boolean draggable(WebDriver driver, WebElement source, int x, int y) {
        try {
            new Actions(driver).dragAndDropBy(source, x, y).perform();
            System.out.println("Draggable Action is performed");
            return true;
        } catch (Exception e) {
            System.out.println("Draggable action is not performed");
            return false;
        }
    }

    // Drag and drop
    public boolean dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        try {
            new Actions(driver).dragAndDrop(source, target).perform();
            System.out.println("DragAndDrop Action is performed");
            return true;
        } catch (Exception e) {
            System.out.println("DragAndDrop action is not performed");
            return false;
        }
    }

    // Take screenshot
    public String takeScreenshot(WebDriver driver, String filename) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
            return destination;
        } catch (Exception e) {
            System.out.println("Screenshot capture failed");
            return "";
        }
    }

    // Get current time
    public String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
    }
}
