package Test.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;  // Corrected import
import com.avactis.base.BaseClass;
import com.avactis.pageobjects.IndexPage;

public class IndexTest extends BaseClass {

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
    public void verifyLogo() throws Throwable {
        boolean result = indexPage.validateLogo();
        Assert.assertTrue(result); // Added failure message
    }

    @Test
    public void verifyTitle() {
        String actTitle = indexPage.getAvactisTitle();
        Assert.assertEquals(actTitle, "Avactis Demo Store"); // Fixed the expected title to match the actual one
    }
}