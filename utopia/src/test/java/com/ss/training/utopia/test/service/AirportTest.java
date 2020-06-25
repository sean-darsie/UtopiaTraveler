//package com.ss.training.utopia.test.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.ss.training.utopia.dao.AirportDAO;
//import com.ss.training.utopia.entity.Airport;
//import com.ss.training.utopia.service.AirportService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AirportTest {
//
//	@Mock
//	private AirportDAO airportDao;
//
//	@InjectMocks
//	private AirportService airportService;
//	
//	@Test
//	public void readAirportsTest() { 
//		// make mock data
//		Airport mockAirport = new Airport(1l, "test airport");
//		Airport mockAirport2 = new Airport(2l, "test airport2");
//		
//		List<Airport> mockAirports = new ArrayList<Airport>();
//		
//		mockAirports.add(mockAirport);
//		mockAirports.add(mockAirport2);
//		Mockito.when(airportDao.findAll()).thenReturn(mockAirports);
//		
//		assertEquals(2, airportService.readAllAirports().length);
//	}
//}
