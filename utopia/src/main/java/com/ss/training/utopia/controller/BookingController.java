package com.ss.training.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	final String basePath = "/utopia/traveler/";
	
	@PostMapping(path=basePath+"bookings")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		HttpStatus status = HttpStatus.CREATED;
		Booking response = booking;
		
		try {
			bookingService.saveBooking(booking);
		} catch(Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response = null;
		}
		
		return new ResponseEntity<Booking>(response, status);
	}
	
	@RequestMapping(path = "/bookings/{id}/purchased/{active}")
	public ResponseEntity<Booking[]> readPurchasedTickets(@PathVariable int bookerId, @PathVariable String active) {
		Booking[] bookings = null;
		HttpStatus status = HttpStatus.OK;

		List<Booking> listOfBookings = bookingService.readBookingByBookerId(bookerId, active == "active");
		if (listOfBookings == null)
			status = HttpStatus.NOT_FOUND;
		else {
			bookings = listOfBookings.toArray(new Booking[listOfBookings.size()]);
		}

		return new ResponseEntity<Booking[]>(bookings, status);
	}
	
	@RequestMapping(path = "/bookings/{id}/booked/{active}")
	public ResponseEntity<Booking[]> readOwnedTickets(@PathVariable int travelerId, @PathVariable String active) {
		Booking[] bookings = null;
		HttpStatus status = HttpStatus.OK;

		List<Booking> listOfBookings = bookingService.readBookingByTravelerId(travelerId, active == "active");
		if (listOfBookings == null) // no author with the specified ID exists
			status = HttpStatus.NOT_FOUND;
		else {
			bookings = listOfBookings.toArray(new Booking[listOfBookings.size()]);
		}

		return new ResponseEntity<Booking[]>(bookings, status);
	}
	
}
