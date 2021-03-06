package com.ss.training.utopia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.Airport;
import com.ss.training.utopia.service.AirportService;

@RestController
@RequestMapping("/traveler")
public class AirportController {
	
	@Autowired
	AirportService airportService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path="/airports")
	public ResponseEntity<Airport[]> readAllAirports() {
		HttpStatus status = HttpStatus.OK;
		Airport[] airports = airportService.readAllAirports();
		
		if (airports == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Airport[]>(airports, status);
		}
		if (airports.length == 0) {
			status = HttpStatus.NO_CONTENT;
		}
				
		return new ResponseEntity<Airport[]>(airports, status);
	}
}
