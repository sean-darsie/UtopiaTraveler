package com.ss.training.utopia.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.training.utopia.dao.FlightDAO;
import com.ss.training.utopia.entity.Flight;

@Component
public class FlightService {
	
	@Autowired
	FlightDAO flightDAO;
	
	public Flight[] getBookableFlights(Long departId, Long arriveId, Long travelerId) {
		List<Flight> flights;
		try {
			flights = flightDAO.findBookableFlights(departId, arriveId, travelerId);
		} catch (Throwable t) {
			return null;
		}
		return flights.toArray(new Flight[flights.size()]);
	}
	
	/**
	 * 
	 * @return List of flights that are after the date of now();
	 */
	public Flight[] readAvailableFlights(Long travelerId){
		List<Flight> flights;
		try {
			flights = flightDAO.findAllFlightsByTraveler(travelerId);
		} catch (Throwable t) {
			return null;
		}

		return flights.toArray(new Flight[flights.size()]);
	}

	public Flight getFlightById(Long flightId) {
		// TODO Auto-generated method stub
		Flight flight = null;
		try {
			flight = flightDAO.findByFlightId(flightId);
		} catch (Throwable t) {
			return null;
		}
		return flight;
	}
	
}
