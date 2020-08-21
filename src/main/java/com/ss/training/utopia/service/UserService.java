package com.ss.training.utopia.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ss.training.utopia.dao.UserDAO;
import com.ss.training.utopia.entity.User;

@Component
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Transactional
	public boolean createUser(User user) {
		
		if (user.getUserId() != null) {
			return false;
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		try {
			userDAO.save(user);
		} catch (RuntimeException t) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean updateUser(User user) {
		
		try {
			userDAO.save(user);
		} catch (RuntimeException t) {
			return false;
		}
		return true;
	}
	
	public User findUserByUsername(String username) {
		User user = null;
		
		try { 
			user = userDAO.findByUsername(username);
		} catch (Exception t) { 
			return  null;
		}
		
		return user;
	}
	
	public User findUserByUserId(Long userId) {
		Optional<User> user = null;
		
		try { 
			user = userDAO.findById(userId);
		} catch (Exception t) { 
			return  null;
		}
		User userObj = null;
		try {
			userObj = user.get();
		} catch(NoSuchElementException e) {
			return null;
		}
		
		return userObj;
	}
}
