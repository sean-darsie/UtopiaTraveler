package com.ss.training.utopia.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
