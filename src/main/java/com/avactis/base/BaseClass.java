 package com.avactis.base;

public class BaseClass {

	public static WebDriver driver;
	public LoginPage loginPage;
	public Properties prop;
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty(null, null)
	}
}