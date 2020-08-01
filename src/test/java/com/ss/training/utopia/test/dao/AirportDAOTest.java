package com.ss.training.utopia.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ss.training.utopia.dao.AirportDAO;
import com.ss.training.utopia.entity.Airport;

@DataJpaTest
public class AirportDAOTest {

	@Autowired
	AirportDAO airportDao;
	
	@Autowired
	private TestEntityManager testEntityManager;	
	
	@Test
	public void getAirportById() {
		Long airport1 = 1l;
		Long airport2 = 2l;
		
		String findMe = "airportName";
		String thenFindMe = "other airport name";
		
		Airport airportToFind = new Airport(null, findMe);
		Airport otherAirport = new Airport(null, thenFindMe);
		
		testEntityManager.persist(airportToFind);
		testEntityManager.persist(otherAirport);
		testEntityManager.flush();
		
		assertEquals(airportToFind, airportDao.findByAirportId(airport1));
		assertEquals(otherAirport, airportDao.findByAirportId(airport2));
	}
}
