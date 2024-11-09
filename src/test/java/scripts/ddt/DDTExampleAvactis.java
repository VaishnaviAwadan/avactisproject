package scripts.ddt;
import org.testng.annotations.Test;
import com.avactis.dataprovider.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class DDTExampleAvactis {
	WebDriver driver;
	
  @Test(dataProvider = "dp", dataProviderClass = dataProviders.class)
  public void f(Integer n, String s) {
	  
	  System.out.println("IN F Test" + "n= " + n+ ", s=" +s);
	  
  }
  
  //@Test(dataProvider = "dataFromXls")
  public void loginToSTCToursimWebsite(String user, String pass) {
		     driver.get("https://nichethyself.com/tourism/home.html");
		     WebElement userName = driver.findElement(By.name("username"));
		     userName.sendKeys("user");
		     WebElement password = driver.findElement(By.name("password"));
		     userName.sendKeys("pass");
		     password.submit();
  }
  

	@Test(dataProvider = "dataFromCSV", dataProviderClass = dataProviders.class)
	public void loginToNicheThyselfCSV(String user, String pass) {
		driver.get("https://nichethyself.com/tourism/home.html");
		WebElement userName = driver.findElement(By.name("username"));
		userName.sendKeys(user);
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pass);
		password.submit();
	}
  
  @BeforeMethod
  public void beforeMethod() {
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

  
  }

