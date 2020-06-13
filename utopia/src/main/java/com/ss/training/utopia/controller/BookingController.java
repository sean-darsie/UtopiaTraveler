package com.ss.training.utopia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}
