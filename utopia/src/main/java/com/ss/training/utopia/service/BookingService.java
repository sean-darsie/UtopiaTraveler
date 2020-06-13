package com.ss.training.utopia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.training.utopia.dao.BookingDAO;
import com.ss.training.utopia.entity.Booking;

@Component
public class BookingService {

	@Autowired
	BookingDAO bookingDAO;
	
	public void saveBooking(Booking booking) {
		bookingDAO.save(booking);
	}
	
	public void updateBooking(Booking booking) {
		bookingDAO.save(booking);
	}
	
	public List<Booking> readBookings() {
		List<Booking> bookings = bookingDAO.
				findAll().
				stream().
				filter(b -> b.isActive() == true).
				collect(Collectors.toList()); 
		
		return bookings;
	}
}
