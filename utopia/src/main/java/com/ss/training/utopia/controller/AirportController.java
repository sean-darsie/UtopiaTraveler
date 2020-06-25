package com.ss.training.utopia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.Airport;
import com.ss.training.utopia.service.AirportService;

@RestController
@RequestMapping("/traveler")
public class AirportController {
	
	@Autowired
	AirportService airportService;
	
	@RequestMapping(path="/airports")
	public ResponseEntity<Airport[]> readAllAirports() {
		HttpStatus status = HttpStatus.OK;
		Airport[] airports = airportService.readAllAirports();
		
		if (airports == null || airports.length == 0) {
			status = HttpStatus.NO_CONTENT;
		}
				
		return new ResponseEntity<Airport[]>(airports, status);
	}
}
