package com.bus.booking.Tests;

import org.testng.annotations.Test;

import com.bus.booking.Base.TestBase;
import com.bus.booking.Pages.HomePage;
import com.bus.booking.Pages.SearchResultPage;

public class Test1 extends TestBase{
	
	@Test(priority = 0)
	public void bookAticket() throws Exception
	{
		HomePage homePage = new HomePage();		
		//HomePgae >> Enter the date, source and destination
		homePage.goTo()
		.booATicket("2020-07-20", "Cameron Highlands", "Kuala Lumpur");
		
		SearchResultPage searchPage = new SearchResultPage();
		//Select min fare >> select max of 6 available seat
		searchPage.selectMinAdultFare();
		searchPage.selectAvailableSeats(6);
	}

}
