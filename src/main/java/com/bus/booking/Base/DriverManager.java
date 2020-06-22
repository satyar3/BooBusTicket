package com.bus.booking.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager
{

	private static WebDriver driver;

	//get the instance of driver
	public static WebDriver getDriver()
	{
		return driver;
	}

	public static void setDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

}
