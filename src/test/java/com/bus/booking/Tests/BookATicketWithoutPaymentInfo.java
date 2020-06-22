package com.bus.booking.Tests;

import org.testng.annotations.Test;

import com.bus.booking.Base.TestBase;
import com.bus.booking.Pages.BookingDetails;
import com.bus.booking.Pages.HomePage;
import com.bus.booking.Pages.SearchResultPage;

public class BookATicketWithoutPaymentInfo extends TestBase
{

	@Test(priority = 0)
	public void bookAticket() throws Exception
	{
		//HomePgae >> Enter the date, source and destination
		HomePage homePage = new HomePage();
		homePage.goTo().booATicket("2020-07-20", "Cameron Highlands", "Kuala Lumpur");

		//Select min fare >> select max of 6 available seat
		SearchResultPage searchPage = new SearchResultPage();
		searchPage.waitTillPageIsReady("Cameron Highlands to Kuala Lumpur buses");
		searchPage.selectMinAdultFare();
		searchPage.selectAvailableSeats(6);

		//Enter passenger details and submit and assert the alert text
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.waitTillPageIsReady("Booking Process | BusOnlineTicket.com");
		bookingDetails.fillPassengerDetails("test", "9632587412", "test@mail.com");
		bookingDetails.validateAlertText("Please select a payment method");
	}

}
