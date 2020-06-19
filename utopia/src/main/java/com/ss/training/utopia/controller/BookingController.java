package com.ss.training.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.Flight;
import com.ss.training.utopia.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	final String basePath = "/utopia/traveler/";
	
	@PostMapping(path=basePath+"bookings")
	public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
		HttpStatus status = HttpStatus.CREATED;
		String response = "???";
		System.out.println("Flight arrive Id "+booking.getFlightId());

		try {
			boolean booked = bookingService.purchaseFlight(booking.getFlightId(), booking.getBookerId(), booking.getTravelerId(), booking.getStripeId());
			if (booked == true)
				response = "Created";
		} catch(Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response = "failed to book flight";
		}
		
		return new ResponseEntity<String>(response, status);
	}
	
	@PutMapping(path=basePath+"bookings")
	public ResponseEntity<String> updateBooking(@RequestBody Booking booking) {
		bookingService.updateBooking(booking);
		return new ResponseEntity<String>("good work m8", HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(path =basePath+"bookings/{id}/purchased")
	public ResponseEntity<Booking[]> readPurchasedTickets(@PathVariable int bookerId) {
		Booking[] bookings = null;
		HttpStatus status = HttpStatus.OK;

		bookings = bookingService.readActiveBookingByBookerId(bookerId);
		if (bookings == null)
			status = HttpStatus.NOT_FOUND;
	
		return new ResponseEntity<Booking[]>(bookings, status);
	}
	
	@RequestMapping(path = "/bookings/{id}/booked/{active}")
	public ResponseEntity<Booking[]> readOwnedTickets(@PathVariable int travelerId, @PathVariable String active) {
		Booking[] bookings = null;
		HttpStatus status = HttpStatus.OK;

		bookings = bookingService.readActiveBookingByTravelerId(travelerId);
		if (bookings == null || bookings.length < 1) // no author with the specified ID exists
			status = HttpStatus.NOT_FOUND;

		return new ResponseEntity<Booking[]>(bookings, status);
	}
	
}
