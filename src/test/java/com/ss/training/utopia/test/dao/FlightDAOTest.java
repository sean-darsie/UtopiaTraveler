package com.ss.training.utopia.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ss.training.utopia.dao.FlightDAO;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.Flight;

@DataJpaTest
public class FlightDAOTest {
	
//	@Autowired
//	TestEntityManager testEntityManager;
//	
//	@Autowired
//	FlightDAO flightDao;
//	
	
//	@Test
//	public void findActiveFlights() {
//		final Long HOUR = 3_600_000l;
//		
//		long now = Instant.now().toEpochMilli();
//		
//		Long activeFlightdId = 1l;
//		Long inactiveFlightId = 2l;
//		
//		Flight activeFlight = new Flight(1l,2l,new Timestamp(now + HOUR), 20 ,100f, activeFlightdId);
//		Flight inactiveFlight = new Flight(1l,2l,new Timestamp(now - HOUR), 20 ,100f, inactiveFlightId);
//		Booking inactiveBooking = new Booking(1l, inactiveFlightId, 1l, false, null);
//
//		testEntityManager.persist(activeFlight);
//		testEntityManager.persist(inactiveFlight);
//		testEntityManager.persist(inactiveBooking);
//		testEntityManager.flush();
//		
//		assertEquals(1, flightDao.findBookableFlights(1l, 2l, 1l).size());
//	}
}
