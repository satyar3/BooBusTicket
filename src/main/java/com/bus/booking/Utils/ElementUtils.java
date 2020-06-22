package com.bus.booking.Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bus.booking.Base.DriverManager;

import io.netty.handler.timeout.TimeoutException;

public class ElementUtils {

	private static WebDriver driver = DriverManager.getDriver();

	//Utils for sendKeys
	public static void sendKeys(By element, String text) {
		try {
			driver.findElement(element).clear();
			driver.findElement(element).sendKeys(text);
			driver.findElement(element).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Util for click action
	public static void click(By element) {
		try {
			driver.findElement(element).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 
	 * @param elementTobeFilled -- locator of the textbox
	 * @param elementTobeSearched -- locator of the searchresult
	 * @param text -- text to be filled
	 */
	
	public static void fillAutocomplete(By elementTobeFilled, By elementTobeSearched, String text)
	{
		try {
			Actions action = new Actions(driver);
			driver.findElement(elementTobeFilled).click();
			action.moveToElement(driver.findElement(elementTobeFilled)).sendKeys(text).build().perform();
			
			List<WebElement> resultList = driver.findElements(elementTobeSearched);

			WebElement result = resultList.stream()
					.filter(el -> el.getText().toLowerCase().equalsIgnoreCase(text.toLowerCase()))
					.findAny().orElse(null);

			result.click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * Wait till the given element is visible
	 * 
	 * @param element
	 * @param timeOut
	 */
	
	
	public static void watiTillVisible(By element, long timeOut)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.ignoring(TimeoutException.class).until(ExpectedConditions.visibilityOf(driver.findElement(element)));
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

}
