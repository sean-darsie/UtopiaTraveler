package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.Airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDAO extends JpaRepository<Airport, Long> {
	Airport findByAirportId(Integer airportId);
}
