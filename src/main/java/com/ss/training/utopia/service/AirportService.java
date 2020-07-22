package com.ss.training.utopia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.training.utopia.dao.AirportDAO;
import com.ss.training.utopia.entity.Airport;

@Component
public class AirportService {
	
	@Autowired
	AirportDAO airportDAO;
	
	public Airport[] readAllAirports() {
		List<Airport> airports;
		try {
			airports = airportDAO.findAll();
		} catch (Throwable t) {
			return null;
		}
		return airports.toArray(new Airport[airports.size()]);	
	}
}
