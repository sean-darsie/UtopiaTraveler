package com.ss.training.utopia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.utopia.entity.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Long> {
	List<Booking> findBookingsById(int travelerId);
}
