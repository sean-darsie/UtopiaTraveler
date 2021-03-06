package com.ss.training.utopia.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.ss.training.utopia.dao.FlightDAO;
import com.ss.training.utopia.entity.Flight;
import com.ss.training.utopia.service.FlightService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class FlightServiceTest {

	@Mock
	FlightDAO flightDAO;
	
	@InjectMocks
	FlightService flightService;
	
	@Test
	public void testGetBookableFlights() {
		// create mock data. 	
		Timestamp departTime = Timestamp.from(Instant.now());
		
		Flight flight1 = new Flight(1l,2l, departTime, 20, 100.0f, 1l);
		Flight flight2 = new Flight(3l,4l, departTime, 20, 100.0f, 2l);
		
		List<Flight> mockFlights = new ArrayList<Flight>();
		mockFlights.add(flight1);
		
		Mockito.when(flightDAO.findBookableFlights(1l, 2l, 1l)).thenReturn(mockFlights);
		assertEquals(1,flightService.getBookableFlights(1l, 2l, 1l).length);
	}
	
	@Test
	public void readAvailableFlights() {
		// flightDAO.findAllFlightsByTraveler(travelerId);
		// create mock data
		Timestamp departTime = Timestamp.from(Instant.now());

		Flight flight1 = new Flight(1l,2l, departTime, 20, 100.0f, 1l);
		Flight flight2 = new Flight(3l,4l, departTime, 20, 100.0f, 2l);
		List<Flight> mockFlights = new ArrayList<Flight>();
		mockFlights.add(flight1);
		mockFlights.add(flight2);
		
		// successful return case
		Mockito.when(flightDAO.findAllFlightsByTraveler(1l)).thenReturn(mockFlights);
		assertEquals(2, flightService.readAvailableFlights(1l).length);
		
		// mysql exception thrown
		Mockito.when(flightDAO.findAllFlightsByTraveler(1l)).thenThrow(new RuntimeException());
		assertEquals(null, flightService.readAvailableFlights(1l));
	}
	
	@Test
	public void getFlightByIdSuccess() {
		Timestamp departTime = Timestamp.from(Instant.now());
		Flight flight = new Flight(1l,2l, departTime, 20, 100.0f, 1l);

		Mockito.when(flightDAO.findByFlightId(1l)).thenReturn(flight);
		assertEquals(flight, flightService.getFlightById(1l));
	}
	
	@Test
	public void getFlightByIdDatabaseFail() {
		Mockito.when(flightDAO.findByFlightId(1l)).thenReturn(null);
		
		assertEquals(null, flightService.getFlightById(1l));
	}
}
