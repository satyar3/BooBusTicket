package com.bus.booking.Pages;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bus.booking.Base.DriverManager;
import com.bus.booking.Utils.ElementUtils;

public class SearchResultPage {
	
	WebDriver driver = null;
	//SearchResultPage searchPage = null;	
	private static final long TIME_OUT = 10;	
	
	private final By ADULT_FARE = By.cssSelector(".busprice1");
	private final By AVAILABLE_SEAT_SELCTION = By.cssSelector(".seat_panel1 .seat_available");
	private final By PROCEED_WITH_SELECTION = By.cssSelector(".seatproceed");

	public SearchResultPage() {		
		driver = DriverManager.getDriver();
		//searchPage = new SearchResultPage();
	}
	
	/**
	 * Min adultfare
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public void selectMinAdultFare() throws Exception
	{
		List<WebElement> adultPriceList = driver.findElements(ADULT_FARE);
		
		String minPrice = adultPriceList.stream()
		.map(el -> el.getText())
		.map(price -> price.replaceAll("[^0-9.]", ""))
		.sorted()
		.min(Comparator.naturalOrder())
		.orElse(null);
		
		String selectSeatLocator = "//div[@class='busprice1' and contains(text(),'"+minPrice+"')]//ancestor::tr[@class='bustr1']//descendant::a[contains(@class,'selectseatbutton']";
		
		List<WebElement> listOfMinPriceBuses = driver.findElements(By.xpath(selectSeatLocator));
		
		Optional<WebElement> minPriceSelectbutton = listOfMinPriceBuses.stream()
		.findFirst();
		
		if(minPriceSelectbutton.isPresent())
			minPriceSelectbutton.get().click();
		else
			throw new Exception("Exception while clicking on the min price seat");
	
	}
	
	/**
	 * Select max of 6 available seats if available else select whatever is max available
	 * 
	 * @param maxSeats
	 * @return
	 */
	
	public void selectAvailableSeats(int maxSeats)
	{
		List<WebElement> availableSeats = driver.findElements(AVAILABLE_SEAT_SELCTION);
		
		if(availableSeats.size() > 6)
			maxSeats = 6; 
		else 
			maxSeats = availableSeats.size();
		
		availableSeats.stream()
		.limit(maxSeats)
		.forEach(el -> el.click());
		
		
		ElementUtils.watiTillVisible(PROCEED_WITH_SELECTION, TIME_OUT);
		ElementUtils.click(PROCEED_WITH_SELECTION);
	}
	

}
