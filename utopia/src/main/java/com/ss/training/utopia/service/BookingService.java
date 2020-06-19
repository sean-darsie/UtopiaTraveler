package com.ss.training.utopia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.training.utopia.dao.BookingDAO;
import com.ss.training.utopia.dao.FlightDAO;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.Flight;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
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
		Booking[] allBookings = null;
		List<Booking> bookings;
		try {
			bookings = bookingDAO.findActiveBookingsByTravelerId(travelerId);
		} catch (Exception e) {
			return allBookings;
		}
		
		allBookings = bookings.toArray(new Booking[bookings.size()]);
		return allBookings;
	}
	
	public boolean purchaseFlight(long flightId, Long bookerId, Long travelerId, String token) {
		Stripe.apiKey = "sk_test_51GvUChBYMFlMJbBRkCzD53Al0tYru5Zt7llUsjsbtfNH5TwY260VumPrZY6tK7481wgyUyTWalT1wQzQ2NNo5qTq00kZoYofR1";
		Charge charge;
		ChargeCreateParams params =
				  ChargeCreateParams.builder()
				    .setAmount(100L)
				    .setCurrency("usd")
				    .setDescription("Example charge")
				    .setSource(token)
				    .build();
		
		try {
			charge = Charge.create(params);
		} catch (StripeException e) {
			return false;
		}
		
		System.out.println(charge.toString());
		// create new booking to put in the database;
		Booking newBooking = new Booking(travelerId, flightId, bookerId, true, charge.getId());

		try {
			bookingDAO.save(newBooking);
		} catch (Exception e) {
			return false;
		}
		
		try {
			flightDAO.reduceSeatsAvailable(flightId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	public boolean cancelFlight(Flight flight, String token) {
		return true;
	}
	
}
