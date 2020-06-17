package com.ss.training.utopia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.utopia.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
	User findByUserId(Integer userId);
}
