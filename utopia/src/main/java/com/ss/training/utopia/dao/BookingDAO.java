package com.ss.training.utopia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.BookingPk;

@Repository
public interface BookingDAO extends JpaRepository<Booking, BookingPk> {

}
