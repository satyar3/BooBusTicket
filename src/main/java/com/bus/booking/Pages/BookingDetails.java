package com.bus.booking.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.bus.booking.Base.DriverManager;
import com.bus.booking.Utils.ElementUtils;

public class BookingDetails
{
	WebDriver driver;
	private final long TIME_OUT = 10;

	private final By FULL_NAME_INPUT = By.cssSelector("#ContentPlaceHolder1_txtName");
	private final By PHONE_NUM_INPUT = By.cssSelector("#ContentPlaceHolder1_txtPhone");
	private final By EMAIL_NUM_INPUT = By.cssSelector("#ContentPlaceHolder1_txtEmail");
	private final By CONF_EMAIL_NUM_INPUT = By.cssSelector("#ContentPlaceHolder1_txtEmail1");
	private final By PROCEED_TO_PAYMENT = By.cssSelector("[name*='btnProceedPayment']");

	public BookingDetails()
	{
		driver = DriverManager.getDriver();
	}

	/**
	 * 
	 * Wait till the expected page title is available
	 * 
	 * @param titile
	 */

	public void waitTillPageIsReady(String titile)
	{
		ElementUtils.witTillPageIsReady(titile, TIME_OUT);
	}

	/**
	 * 
	 * fill passenger booking details
	 * 
	 * @param fullName
	 * @param mobileNumber
	 * @param eMail
	 */

	public void fillPassengerDetails(String fullName, String mobileNumber, String eMail)
	{
		ElementUtils.watiTillVisible(FULL_NAME_INPUT, TIME_OUT);
		ElementUtils.sendKeys(FULL_NAME_INPUT, fullName);
		ElementUtils.sendKeys(PHONE_NUM_INPUT, mobileNumber);
		ElementUtils.sendKeys(EMAIL_NUM_INPUT, eMail);
		ElementUtils.sendKeys(CONF_EMAIL_NUM_INPUT, eMail);
		//ElementUtils.scrollIntoView(PROCEED_TO_PAYMENT);
		ElementUtils.click(PROCEED_TO_PAYMENT);
	}

	/**
	 * 
	 * Validate the alert text
	 * 
	 * @param expectedAlertText
	 */

	public void validateAlertText(String expectedAlertText)
	{
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), expectedAlertText);
		alert.dismiss();
	}

}
