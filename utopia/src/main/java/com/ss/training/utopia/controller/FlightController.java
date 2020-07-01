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
	
	
	@RequestMapping(path="/travelers/{travelerId}/flights")
	public ResponseEntity<Flight[]> readAvailableFlights(@PathVariable Long travelerId) {
		HttpStatus status = HttpStatus.OK;
		Flight[] flightArray = null;
		
		flightArray = flightService.readAvailableFlights(travelerId);
		
		if (flightArray == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Flight[]>(flightArray, status);
		}
		if (flightArray.length == 0) 
			status = HttpStatus.NO_CONTENT;
			
		return new ResponseEntity<Flight[]>(flightArray, status);
	}
	
	
	@RequestMapping(path="/travelers/{travelerId}/flights/departure/{departId}/arrival/{arriveId}")
	public ResponseEntity<Flight[]> readBookableFlights(@PathVariable Long travelerId,@PathVariable Long departId,@PathVariable Long arriveId ) {
		HttpStatus status = HttpStatus.OK;
		Flight[] flights = flightService.getBookableFlights(departId, arriveId, travelerId);
		
		if (flights == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Flight[]>(flights, status);
		}
		if (flights.length == 0) {
			status = HttpStatus.NO_CONTENT;
		}
		
		
		return new ResponseEntity<Flight[]>(flights, status);
	}
	
}
