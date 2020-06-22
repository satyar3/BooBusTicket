package com.bus.booking.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
	
	protected WebDriver driver = null;
	
	@BeforeTest
	public void setUp()
	{
		DriverManager.setDriver();
		driver = DriverManager.getDriver();
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
