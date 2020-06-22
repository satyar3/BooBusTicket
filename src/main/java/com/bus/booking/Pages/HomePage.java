package com.bus.booking.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bus.booking.Base.DriverManager;
import com.bus.booking.Utils.ElementUtils;

public class HomePage {
	
	WebDriver driver = null;	
	private static final long TIME_OUT = 10;
	
	private final By DEPARTURE_DATE_INPUT = By.cssSelector("#ContentPlaceHolder1_txtDepartDate");
	private final By ORIGIN_INPUT = By.cssSelector("#txtOrigin");
	private final By DESTINATION_INPUT = By.cssSelector("#txtDestination");
	private final By LIST_OF_SOURCE = By.cssSelector(".select2-result-label");
	private final By BOOK_BTN = By.cssSelector("#btnBusSearchNew");
	private final By DATE_PICKER_ICON = By.cssSelector("#ui-datepicker-div");
	private final By DATE_PICKER_CAL = By.cssSelector("#ui-datepicker-div");
	
	public HomePage() {		
		driver = DriverManager.getDriver();
	}
	
	//Lunch the URL
	public HomePage goTo()
	{
		HomePage pg  = new HomePage();
		driver.get("https://www.busonlineticket.com/booking/bus-tickets.aspx");
		return pg;
	}
	
	/**
	 * Ticket booking based on date
	 * 
	 * 
	 * @param date -- YYYY-MM-DD
	 * @param source
	 * @param destination
	 * @return 
	 */
	
	
	public SearchResultPage booATicket(String date, String source, String destination)
	{
		ElementUtils.sendKeys(DEPARTURE_DATE_INPUT, date);
		ElementUtils.watiTillVisible(DATE_PICKER_CAL, TIME_OUT);
		ElementUtils.watiTillVisible(DATE_PICKER_ICON, TIME_OUT);
		//ElementUtils.click(DATE_PICKER_ICON);
		ElementUtils.fillAutocomplete(ORIGIN_INPUT, LIST_OF_SOURCE, source);
		ElementUtils.fillAutocomplete(DESTINATION_INPUT, LIST_OF_SOURCE, destination);
		ElementUtils.click(BOOK_BTN);
		
		return new SearchResultPage();
	}

}
