package com.ss.training.utopia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.utopia.entity.Flight;

@Repository
public interface FlightDAO extends JpaRepository<Flight, Long> {
	/**
	 * 
	 * @param departCityId
	 * @param arriveCityId
	 * @return list of flights that match the given departure and arrival city ids
	 */
	List<Flight> findAvailableFlights(Integer departId, Integer arriveId);
}
