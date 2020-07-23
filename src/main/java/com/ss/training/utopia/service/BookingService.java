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
	
	public void saveBooking(Booking booking) {
		bookingDAO.save(booking);
	}
	
	public void updateBooking(Booking booking) {
		bookingDAO.save(booking);
	}
	
	public List<Booking> readBookings() {
		List<Booking> bookings = bookingDAO.
				findAll().
				stream().
				filter(b -> b.isActive() == true).
				collect(Collectors.toList()); 
		
		return bookings;
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
		} catch (Exception e) {
			return activeBookings;
		}
		
		activeBookings = bookings.toArray(new Booking[bookings.size()]);
		return activeBookings;
	}
	
	@Transactional
	public Booking purchaseFlight(long flightId, Long bookerId, Long travelerId, String token) throws StripeException {
		Stripe.apiKey = System.getenv("STRIPE_KEY");
		Flight flight = flightDAO.findByFlightId(flightId);
		Charge charge;
		
		ChargeCreateParams params =
				  ChargeCreateParams.builder()
				    .setAmount(100l)
				    .setCurrency("usd")
				    .setDescription("Example charge")
				    .setSource(token)
				    .build();
		
		charge = Charge.create(params);

		System.out.println(charge.toString());
		// create new booking to put in the database;
		Booking newBooking = new Booking(travelerId, flightId, bookerId, true, charge.getId());
		flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
		
		
		bookingDAO.save(newBooking);
		flightDAO.save(flight);

		return newBooking;
	}
	
	@Transactional
	public boolean cancelFlight(Booking booking) throws StripeException {
		Stripe.apiKey = System.getenv("STRIPE_KEY");
		Flight flight = flightDAO.findByFlightId(booking.getFlightId());
		
		Map<String, Object> params = new HashMap<>();
		params.put(
		  "charge",
		  booking.getStripeId()
		);

		Refund refund = Refund.create(params);
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
