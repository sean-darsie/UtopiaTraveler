package com.ss.training.utopia.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ss.training.utopia.dao.BookingDAO;
import com.ss.training.utopia.entity.Booking;

@DataJpaTest
public class BookingTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	BookingDAO bookingDao;
	
//	public Booking( Long travelerId, Long flightId, Long bookerId, boolean active, String stripeId) {
	
	@Test
	public void getActiveBookingsByBookerId() {
		Long flightId = 1l;
		Long bookerId = 1l;
		
		Booking booking = new Booking(7l, flightId, bookerId, true, "stripe ID");
		Booking otherBooking = new Booking(2l,2l,2l,false,"stripeId");
		
		testEntityManager.persist(booking);
		testEntityManager.persist(otherBooking);
		testEntityManager.flush();
		
		assertEquals(1, bookingDao.findActiveBookingsByBookerId(bookerId).size());
	}
	
	@Test
	public void getActiveBookingsByTravelerId() {
		Long flightId = 1l;
		Long travelerId = 1l;
		
		Booking booking = new Booking(travelerId, flightId, 7l, true, "stripe ID");
		Booking otherBooking = new Booking(2l,2l,2l,false,"stripeId");
		
		testEntityManager.persist(booking);
		testEntityManager.persist(otherBooking);
		testEntityManager.flush();
		
		assertEquals(1, bookingDao.findActiveBookingsByTravelerId(travelerId).size());
	}
	
	@Test
	public void getInactiveBookingsByBookerId() {
		Long flightId = 1l;
		Long bookerId = 1l;
		
		Booking booking = new Booking(1l, flightId, bookerId, false, "stripe ID");
		Booking otherBooking = new Booking(2l,2l,2l,false,"stripeId");
		
		testEntityManager.persist(booking);
		testEntityManager.persist(otherBooking);
		testEntityManager.flush();
		
		assertEquals(1, bookingDao.findInactiveBookingsByBookerId(bookerId).size());
	}
	
	@Test
	public void getInactiveBookingsByTravelerId() {
		Long flightId = 1l;
		Long travelerId = 1l;
		
		Booking booking = new Booking(travelerId, flightId, 8l, false, "stripe ID");
		Booking otherBooking = new Booking(2l,2l,2l,false,"stripeId");
		
		testEntityManager.persist(booking);
		testEntityManager.persist(otherBooking);
		testEntityManager.flush();
		
		assertEquals(1, bookingDao.findInactiveBookingsByTravelerId(travelerId).size());
	}
	
	@Test
	public void getAllBookingsByBookerId() {
		Long flightId = 1l;
		Long bookerId= 1l;
		Long otherFlightId = 2l;
		
		Booking booking = new Booking(9l, flightId, bookerId, true, "stripe ID");
		Booking otherBooking = new Booking(2l, otherFlightId, bookerId, false, "stripe ID");

		
		testEntityManager.persist(booking);
		testEntityManager.persist(otherBooking);
		testEntityManager.flush();
		
		Set<Booking> expectedBookings = new HashSet<Booking>();
		expectedBookings.add(booking);
		expectedBookings.add(otherBooking);

		Set<Booking> actualBookings = new HashSet<Booking>(bookingDao.findAllByBookerId(bookerId));

		assertEquals(expectedBookings, actualBookings);
	}
	
	@Test
	public void getAllBookingsByTravelerId() {
		Long flightId = 1l;
		Long travelerId = 1l;
		Long otherFlightId = 2l;
		Long otherTravelerId = 2l;
		
		Booking booking = new Booking(travelerId, flightId, 1l, true, "stripe ID");
		Booking otherBooking = new Booking(travelerId, otherFlightId, 1l, false, "stripe ID");

		
		testEntityManager.persist(booking);
		testEntityManager.persist(otherBooking);
		testEntityManager.flush();
		
		Set<Booking> expectedBookings = new HashSet<Booking>();
		expectedBookings.add(booking);
		expectedBookings.add(otherBooking);

		Set<Booking> actualBookings = new HashSet<Booking>(bookingDao.findAllByTravelerId(travelerId));

		assertEquals(expectedBookings, actualBookings);
	}
}
