package com.ss.training.utopia.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ss.training.utopia.dao.BookingDAO;
import com.ss.training.utopia.dao.FlightDAO;
import com.ss.training.utopia.dao.StripeDAO;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.Flight;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import com.stripe.param.ChargeCreateParams;

@Component
public class BookingService {

	@Autowired
	BookingDAO bookingDAO;
	
	@Autowired
	FlightDAO flightDAO;
	
	@Autowired
	StripeDAO stripeDAO;
	
	public void saveBooking(Booking booking) {
		bookingDAO.save(booking);
	}
	
	public void updateBooking(Booking booking) {
		bookingDAO.save(booking);
	}
	
	/**
	 * 
	 * @param bookerId
	 * @param active
	 * @return
	 */
	public Booking[] readActiveBookingByBookerId(long bookerId) {
		List<Booking> bookings;
		try {
			bookings = bookingDAO.findActiveBookingsByBookerId(bookerId);
		} catch (Exception e) {
			return null;
		}
		return  bookings.toArray(new Booking[bookings.size()]);
	}
	
	/**
	 * 
	 * @param travelerId
	 * @return
	 */
	public Booking[] readActiveBookingByTravelerId(long travelerId) {
		Booking[] activeBookings = null;
		List<Booking> bookings;
		try {
			bookings = bookingDAO.findActiveBookingsByTravelerId(travelerId);
			activeBookings = bookings.toArray(new Booking[bookings.size()]);
		} catch (Exception e) {
			return activeBookings;
		}
		
		return activeBookings;
	}
	
	@Transactional
	public Booking purchaseFlight(long flightId, Long bookerId, Long travelerId, String token) throws StripeException {
		Flight flight = flightDAO.findByFlightId(flightId);
		String stripeToken;
		
		try {
			stripeToken = stripeDAO.purchaseFlight(token, flight);
		} catch(StripeException e) {
			return null;
		}
		
		// create new booking to put in the database;
		Booking newBooking = new Booking(travelerId, flightId, bookerId, true, stripeToken);
		flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
		
		
		bookingDAO.save(newBooking);
		flightDAO.save(flight);

		return newBooking;
	}
	
	@Transactional
	public boolean cancelFlight(Booking booking) throws StripeException {
		Flight flight = flightDAO.findByFlightId(booking.getFlightId());
		
		try {
			stripeDAO.refundFlight(booking.getStripeId());
		} catch (StripeException e) {
			return false;
		}
		
		flight.setSeatsAvailable(flight.getSeatsAvailable() + 1);
				
		try {
			booking.setActive(false);
			bookingDAO.save(booking);
			flightDAO.save(flight);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
