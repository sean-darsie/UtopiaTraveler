package com.ss.training.utopia.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ss.training.utopia.dao.BookingDAO;
import com.ss.training.utopia.dao.FlightDAO;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.service.BookingService;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

	@Mock
	BookingDAO bookingDAO;
	
	@Mock
	FlightDAO flightDAO;
	
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
	public void readActiveBookingByTravelerId() {
		Booking bookingActive = new Booking();
		List<Booking> mockBookings = new ArrayList<Booking>();
		mockBookings.add(bookingActive);
		
		Mockito.when(bookingDAO.findActiveBookingsByTravelerId(1l)).thenReturn(mockBookings);
		assertEquals(1, bookingService.readActiveBookingByTravelerId(1l).length);
	}
	
	@Test
	public void purchaseTicket() { 
		
	}
	
	
	// test refund
}
