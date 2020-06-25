package com.ss.training.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.Flight;
import com.ss.training.utopia.service.FlightService;

@RestController
@RequestMapping(path="/traveler")
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	
	@RequestMapping(path="/flights")
	public ResponseEntity<Flight[]> readAvailableFlights() {
		HttpStatus status = HttpStatus.OK;
		Flight[] flightArray = null;
		
		List<Flight> flights = flightService.readAvailableFlights();
		
		if (flights == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		if (flights.size() == 0) {
			status = HttpStatus.NO_CONTENT;
		} else {
			flightArray = flights.toArray(new Flight[flights.size()]);
		}
		
		return new ResponseEntity<Flight[]>(flightArray, status);
	}
	
	
	@RequestMapping(path="/travelers/{travelerId}/flights/departure/{departId}/arrival/{arriveId}")
	public ResponseEntity<Flight[]> readBookableFlights(@PathVariable Long travelerId,@PathVariable Long departId,@PathVariable Long arriveId ) {
		HttpStatus status = HttpStatus.OK;
		Flight[] flights = flightService.getBookableFlights(departId, arriveId, travelerId);
		
		if (flights == null || flights.length == 0 ) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Flight[]>(flights, status);
	}
	
}
