package com.ss.training.utopia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.utopia.entity.Flight;

@Repository
public interface FlightDAO extends JpaRepository<Flight, Long> {

}
