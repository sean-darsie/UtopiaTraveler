package com.ss.training.utopia.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.ss.training.utopia.dao.BookingDAO;
import com.ss.training.utopia.dao.FlightDAO;
import com.ss.training.utopia.dao.StripeDAO;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.Flight;
import com.ss.training.utopia.service.BookingService;
import com.stripe.exception.StripeException;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class BookingServiceTest {

	@Mock
	BookingDAO bookingDAO;
	
	@Mock
	FlightDAO flightDAO;
	
	@Mock
	StripeDAO stripeDAO;
	
	@InjectMocks
	BookingService bookingService;
	
	@Test
	public void readActiveBookingByBookerId() {
		Booking bookingActive = new Booking();
		List<Booking> mockBookings = new ArrayList<Booking>();
		mockBookings.add(bookingActive);
		
		Mockito.when(bookingDAO.findActiveBookingsByBookerId(1l)).thenReturn(mockBookings);
		assertEquals(1, bookingService.readActiveBookingByBookerId(1l).length);
	}
	
	@Test
	public void purchaseTicket() throws StripeException { 
		// mock data
		String token = "stripe token";
		Booking mockBooking = new Booking(1l, 1l, 1l, true, token);
		Flight mockFlight = new Flight(1l,1l, null, 20, null, null);
		
		Mockito.when(flightDAO.findByFlightId(1l)).thenReturn(mockFlight);
		Mockito.when(stripeDAO.purchaseFlight(token, mockFlight)).thenReturn("stripe id");
		
		assertEquals("stripe id", bookingService.purchaseFlight(1l, 1l, 1l, token).getStripeId());
	}
	
	@Test
	public void refundTicket() throws StripeException { 
		// mock data
		String token = "stripe token";
		Booking mockBooking = new Booking(1l, 1l, 1l, true, token);
		Flight mockFlight = new Flight(1l,1l, null, 20, null, null);
		
		Mockito.when(flightDAO.findByFlightId(1l)).thenReturn(mockFlight);
		bookingService.cancelFlight(mockBooking);
		
		assertEquals(false, mockBooking.isActive());
	}
	
	
	@Test
	public void readActiveBookingByTravelerIdSuccess() {
		Booking bookingActive = new Booking();
		List<Booking> mockBookings = new ArrayList<Booking>();
		mockBookings.add(bookingActive);
		
		Mockito.when(bookingDAO.findActiveBookingsByTravelerId(1l)).thenReturn(mockBookings);
		assertEquals(1, bookingService.readActiveBookingByTravelerId(1l).length);
	}
	
	
	@Test
	public void readActiveBookingByTravelerIdFail() {
		Mockito.when(bookingDAO.findActiveBookingsByTravelerId(1l)).thenThrow(new RuntimeException());

		assertEquals(null, bookingService.readActiveBookingByTravelerId(1l));
	}
	
}
