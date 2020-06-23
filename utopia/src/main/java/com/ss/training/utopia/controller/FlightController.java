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
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	
	@RequestMapping(path="/utopia/traveler/flights")
	public ResponseEntity<Flight[]> readAvailableFlights() {
		HttpStatus status = HttpStatus.OK;
		Flight[] flightArray = null;
		
		List<Flight> flights = flightService.readAvailableFlights();
		
		if (flights.size() < 1 || flights == null) {
			status = HttpStatus.NOT_FOUND;
		}
		else {
			flightArray = flights.toArray(new Flight[flights.size()]);
		}
		
		return new ResponseEntity<Flight[]>(flightArray, status);
	}
	
	@RequestMapping(path="/utopia/traveler/travelers/{travelerId}/flights/departs/{departId}/arrives/{arriveId}")
	public ResponseEntity<Flight[]> readFlightsByDestination(@PathVariable Long departId, @PathVariable Long arriveId, @PathVariable Long travelerId) {
		HttpStatus status = HttpStatus.OK;
		
		Flight[] flights = flightService.getBookableFlights(departId, arriveId, travelerId);
		
		if (flights.length < 1 || flights == null) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Flight[]>(flights, status);
	}
	
	@RequestMapping(path="/utopia/traveler/{travelerId}/flights/departs{departId}/arrives{arriveId}")
	public ResponseEntity<Flight[]> readBookableFlights(@PathVariable Long travelerId,@PathVariable Long departId,@PathVariable Long arriveId ) {
		HttpStatus status = HttpStatus.OK;
		Flight[] flights = flightService.getBookableFlights(travelerId, departId, arriveId);
		
		if (flights.length == 0 || flights == null) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Flight[]>(flights, status);
	}
	
}
