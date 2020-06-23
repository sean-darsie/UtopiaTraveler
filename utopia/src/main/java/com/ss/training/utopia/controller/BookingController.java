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
import com.stripe.exception.StripeException;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	final String basePath = "/utopia/traveler/";
	
	@PostMapping(path=basePath+"bookings")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		HttpStatus status = HttpStatus.CREATED;
		Booking ticket = null;
		try {
			ticket = bookingService.purchaseFlight(booking.getFlightId(), booking.getBookerId(), booking.getTravelerId(), booking.getStripeId());				
		} catch(Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Booking>(ticket, status);
	}
	
	@PutMapping(path=basePath+"bookings")
	public ResponseEntity<String> cancelBooking(@RequestBody Booking booking) {
		HttpStatus status = HttpStatus.ACCEPTED;
		String response = "";
		
		try {
			boolean canceled = bookingService.cancelFlight(booking);
			if (canceled == true)
				response = "Canceled";
			else {
				response = "error";
				status = HttpStatus.INTERNAL_SERVER_ERROR;

			}
		} catch(StripeException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response = "failed to cancel flight";
		}
		
		return new ResponseEntity<String>(response, status);
	}
	
	
	@RequestMapping(path =basePath+"bookings/purchasers/{id}")
	public ResponseEntity<Booking[]> readPurchasedTickets(@PathVariable int bookerId) {
		Booking[] bookings = null;
		HttpStatus status = HttpStatus.OK;

		bookings = bookingService.readActiveBookingByBookerId(bookerId);
		if (bookings == null)
			status = HttpStatus.NO_CONTENT;
	
		return new ResponseEntity<Booking[]>(bookings, status);
	}
	
	@RequestMapping(path = "/bookings/travelers/{id}")
	public ResponseEntity<Booking[]> readOwnedTickets(@PathVariable int travelerId) {
		Booking[] bookings = null;
		HttpStatus status = HttpStatus.OK;

		bookings = bookingService.readActiveBookingByTravelerId(travelerId);
		if (bookings == null || bookings.length < 1) // no author with the specified ID exists
			status = HttpStatus.NO_CONTENT;

		return new ResponseEntity<Booking[]>(bookings, status);
	}
	
}
