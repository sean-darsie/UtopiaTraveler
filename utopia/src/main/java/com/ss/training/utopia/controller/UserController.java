package com.ss.training.utopia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.User;
import com.ss.training.utopia.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	final int maxLength = 45;
	final String basePath = "/utopia/traveler/";
	
	@PostMapping(path=basePath+"users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		HttpStatus status = HttpStatus.CREATED;
		
		if (user.getUsername().length() > maxLength) {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<User>(user, status);
		}
		
		boolean userCreated = userService.createUser(user);
		
		if (userCreated == false) {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<User>(user, status);
		}
		
		user.setPassword(null);
		user.setUserId(null);
		return new ResponseEntity<User>(user, status);
	}
	
	@PutMapping(path=basePath+"users")
	public ResponseEntity<User> updateeUser(@RequestBody User user) {
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if (user.getUsername().length() > maxLength) {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<User>(user, status);
		}
		
		boolean userUpdated = userService.updateUser(user);
		
		if (userUpdated == false) {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<User>(user, status);
		}
		user.setPassword(null);
		user.setUserId(null);
		return new ResponseEntity<User>(user, status);
	}
}
