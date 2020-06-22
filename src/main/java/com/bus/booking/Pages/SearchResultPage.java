package com.bus.booking.Pages;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bus.booking.Base.DriverManager;
import com.bus.booking.Utils.ElementUtils;

public class SearchResultPage
{

	WebDriver driver = null;
	//SearchResultPage searchPage = null;	
	private static final long TIME_OUT = 10;

	private final By BOOKING_SUMMARY = By.cssSelector(".booking-summary");
	private final By ADULT_FARE = By.cssSelector(".busprice1");
	private final By BUS_INNER_TABLE = By.cssSelector(".businnertable");
	private final By AVAILABLE_SEAT_SELCTION = By.cssSelector(".seat_panel1 .seat_available");
	private final By PROCEED_WITH_SELECTION = By.cssSelector(".seatproceed");
	private final By SELECT_SEAT_BTN = By.cssSelector(".selectseatbutton");

	public SearchResultPage()
	{
		driver = DriverManager.getDriver();
		//searchPage = new SearchResultPage();
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
	 * Min adult fare
	 * 
	 * @return
	 * @throws Exception
	 */

	public void selectMinAdultFare() throws Exception
	{
		ElementUtils.watiTillVisible(BOOKING_SUMMARY, TIME_OUT);
		List<WebElement> adultPriceList = driver.findElements(ADULT_FARE);

		//Get Minimum Price
		String minPrice = adultPriceList.stream().map(el -> el.getText()).map(price -> price.replaceAll("[^0-9.]", ""))
				.sorted().min(Comparator.naturalOrder()).orElse(null);

		//Get the index of minimum price
		int indexOpt = IntStream.range(0, adultPriceList.size())
				.filter(i -> adultPriceList.get(i).getText().contains(minPrice)).findFirst().orElse(-1);

		//List of select buttons
		List<WebElement> selectseatButtonList = driver.findElements(SELECT_SEAT_BTN);

		//Click on the select button having min price
		selectseatButtonList.stream().skip(indexOpt).findFirst().orElse(null).click();

		ElementUtils.watiTillVisible(BUS_INNER_TABLE, TIME_OUT);
	}

	/**
	 * Select max of 6 available seats if available else select whatever is max available
	 * 
	 * @param maxSeats
	 * @return
	 */

	public void selectAvailableSeats(int maxSeats)
	{
		ElementUtils.watiTillVisible(BUS_INNER_TABLE, TIME_OUT);
		List<WebElement> availableSeats = driver.findElements(AVAILABLE_SEAT_SELCTION);

		if (availableSeats.size() > 6)
			maxSeats = 6;
		else
			maxSeats = availableSeats.size();

		availableSeats.stream().limit(maxSeats).forEach(el -> el.click());

		ElementUtils.watiTillVisible(PROCEED_WITH_SELECTION, TIME_OUT);
		ElementUtils.click(PROCEED_WITH_SELECTION);
	}

}
