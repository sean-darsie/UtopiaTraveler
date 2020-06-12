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
	
	public List<Flight> readFlights(){
		return flightDAO.findAll();
	}
	
	/**
	 * 
	 * @param departId
	 * @param arriveId
	 * @return a list of flights from depart city to arrive city past today's date.
	 */
	public List<Flight> readFlightsByDestination(Integer departId,Integer arriveId){
		List<Flight> flights = flightDAO.
				findAll().
				stream().
				filter(f -> (f.getDepartId() == departId && f.getArriveId() == arriveId)).
				filter(f -> f.getDepartTime().after(Timestamp.valueOf(LocalDateTime.now()))).
				collect(Collectors.toList());
		
		return flights;
	}
	
	/**
	 * 
	 * @return List of flights that are after the date of now();
	 */
	public List<Flight> readAvailableFlights(){
		List<Flight> flights = flightDAO.
				findAll().
				stream().
				filter(f -> f.getDepartTime().after(Timestamp.valueOf(LocalDateTime.now()))).
				collect(Collectors.toList());

		return flights;
	}
	
}
