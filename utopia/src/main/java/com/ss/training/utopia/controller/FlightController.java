package com.ss.training.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.Flight;
import com.ss.training.utopia.service.FlightService;

@RestController
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	final String rootPath = "/utopia/traveler/";
	
	@RequestMapping(path=rootPath+"flights")
	public ResponseEntity<Flight[]> readFlights() {
		HttpStatus status = HttpStatus.OK;
		Flight[] flightArray = null;
		
		List<Flight> flights = flightService.readFlights();
		
		if (flights.size() < 1 || flights == null) {
			status = HttpStatus.NOT_FOUND;
		}
		else {
			flightArray = flights.toArray(new Flight[flights.size()]);
		}
		
		return new ResponseEntity<Flight[]>(flightArray, status);
	}
	
}
