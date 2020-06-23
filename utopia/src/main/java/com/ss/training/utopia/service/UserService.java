package com.ss.training.utopia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.training.utopia.dao.UserDAO;
import com.ss.training.utopia.entity.User;

@Component
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public boolean createUser(User user) {
		
		if (user.getUserId() != null) {
			return false;
		}
		
		try {
			userDAO.save(user);
		} catch (Exception t) {
			return false;
		}
		return true;
	}
	
	public boolean updateUser(User user) {
		
		try {
			userDAO.save(user);
		} catch (Exception t) {
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
