package com.ss.training.utopia.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ss.training.utopia.entity.Flight;
import com.ss.training.utopia.entity.FlightPk;

@Repository
public interface FlightDAO extends JpaRepository<Flight, FlightPk> {

	/**
	 * Find available flights from one destination to another
	 * @param departId
	 * @param arriveId
	 * @param travelerId
	 * @return
	 */
	@Query("SELECT f FROM Flight f WHERE f.departId = ?1 AND f.arriveId = ?2 "
			+ "AND f.departTime > CURRENT_TIMESTAMP AND f.seatsAvailable > 0 "
			+ "AND f.flightId NOT IN (SELECT b.flightId FROM Booking b WHERE b.travelerId = ?3 AND b.active = true)")
	public List<Flight> findBookableFlights(Long departId, Long arriveId, Long travelerId);

	/**
	 * Reduce the number of seats available by 1
	 * @param flightId
	 */
	@Query("UPDATE Flight SET seatsAvailable = seatsAvailable - 1 WHERE flightId = ?2")
	public void reduceSeatsAvailable(Long flightId);
	
	/**
	 * Increase the number of seats available by 1
	 * @param flightId
	 */
	@Query("UPDATE Flight SET seatsAvailable = seatsAvailable + 1 WHERE flightId = ?2")
	public void increaseSeatsAvailable(Long flightId);

	public Flight findByFlightId(Long flightId);
}
