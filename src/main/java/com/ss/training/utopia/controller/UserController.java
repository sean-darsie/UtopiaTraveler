package com.ss.training.utopia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.utopia.entity.User;
import com.ss.training.utopia.service.UserService;

@RestController
@RequestMapping(path="/traveler")
public class UserController {

	@Autowired
	UserService userService;
	
	final int maxLength = 45;
	
	@PostMapping(path="/users")
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
		return new ResponseEntity<User>(user, status);
	}
	
	@RequestMapping(path="/users/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		HttpStatus status = HttpStatus.OK;
		
		User user = userService.findUserByUsername(username);
		
		if (user == null) {
			status = HttpStatus.NO_CONTENT;
			return new ResponseEntity<User>(user, status);
		}
		user.setPassword(null);
		return new ResponseEntity<User>(user, status);
	}
	
	@RequestMapping(path="/travelers/{userId}")
	public ResponseEntity<User> getUserByUserid(@PathVariable Long userId) {
		HttpStatus status = HttpStatus.OK;
		
		User user = userService.findUserByUserId(userId);
		
		if (user == null) {
			status = HttpStatus.NO_CONTENT;
			return new ResponseEntity<User>(user, status);
		}
		user.setPassword(null);
		return new ResponseEntity<User>(user, status);
	}
	
	@PutMapping(path="/users")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		HttpStatus status = HttpStatus.NO_CONTENT;
		
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
		return new ResponseEntity<User>(user, status);
	}
}
