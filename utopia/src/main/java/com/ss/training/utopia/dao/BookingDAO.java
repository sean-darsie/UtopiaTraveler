package com.ss.training.utopia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.BookingPk;

@Repository
public interface BookingDAO extends JpaRepository<Booking, BookingPk> {
	
	@Query("SELECT b FROM Booking b WHERE b.travelerId = ?1 AND b.active = 1")
	public List<Booking> findActiveBookingsByTravelerId(Long travelerId);
	
	@Query("SELECT b FROM Booking b WHERE b.travelerId = ?1 AND b.active = 0")
	public List<Booking> findInactiveBookingsByTravelerId(Long travelerId);

	@Query("SELECT b FROM Booking b WHERE b.bookerId = ?1 AND b.active = 1")
	public List<Booking> findActiveBookingsByBookerId(Long bookerId);
	
	@Query("SELECT b FROM Booking b WHERE b.bookerId = ?1 AND b.active = 0")
	public List<Booking> findInactiveBookingsByBookerId(Long bookerId);
	
	@Query("SELECT b FROM Booking b WHERE b.bookerId = ?1")
	public List<Booking> findAllByBookerId(Long bookerId);
	
	@Query("SELECT b FROM Booking b WHERE b.travelerId = ?1")
	public List<Booking> findAllByTravelerId(Long travelerId);
	
	
	
}
