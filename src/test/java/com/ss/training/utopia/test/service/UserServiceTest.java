package com.ss.training.utopia.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.ss.training.utopia.dao.UserDAO;
import com.ss.training.utopia.entity.User;
import com.ss.training.utopia.service.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {
	
	@Mock
	UserDAO userDAO;
	
	@InjectMocks
	UserService userService;
	
	// create user
	@Test
	public void createUserWhenIdIsSet() {
		// create mockdata
		User user = new User(1l, "username","name","password","role");
		Mockito.when(userDAO.save(user)).thenReturn(new User());
		
		assertFalse(userService.createUser(user));
	}
	
	@Test
	public void createUserSuccessCase() {
		// create mockdata
		User user = new User(null, "username","name","password","role");
		Mockito.when(userDAO.save(user)).thenReturn(new User());
		
		assertTrue(userService.createUser(user));
	}
	
	@Test
	public void createUserDatabaseFails() {
		// create mockdata
		User user = new User(null, "username","name","password","role");
		Mockito.when(userDAO.save(user)).thenThrow(new RuntimeException());
		
		assertFalse(userService.createUser(user));
	}
	
	@Test
	public void updateUserSuccessful() {
		// create mockdata
		User user = new User(1l, "username","name","password","role");
		Mockito.when(userDAO.save(user)).thenReturn(new User());
		
		assertTrue(userService.updateUser(user));
	}
	
	@Test
	public void updateUserDatabaseFails() {
		// create mockdata
		User user = new User(1l, "username","name","password","role");
		Mockito.when(userDAO.save(user)).thenThrow(new RuntimeException());
		
		assertFalse(userService.updateUser(user));
	}
	
	// find user by username
	@Test
	public void findUserByUsernameWhenUserExists() {
		User user = new User(1l, "username","name","password","role");
		Mockito.when(userDAO.findByUsername("username")).thenReturn(user);
		
		assertEquals(user, userService.findUserByUsername("username"));
	}
	
	@Test
	public void findUserByUsernameWhenDoesNotExist() {
		Mockito.when(userDAO.findByUsername("username")).thenThrow(new RuntimeException());
		
		assertEquals(null, userService.findUserByUsername("username"));
	}
	
	// find user by id
	@Test
	public void findUserByIdWhenUserExists() {
		User user = new User(1l, "username","name","password","role");

		Optional<User> user2 = Optional.of(user);
		Mockito.when(userDAO.findById(1l)).thenReturn(user2);
		
		assertEquals(user, userService.findUserByUserId(1l));
	}
	
	@Test
	public void findUserByIdWhenUserDoesNotExist() {
		Mockito.when(userDAO.findById(1l)).thenThrow(new RuntimeException());
		
		assertEquals(null, userService.findUserByUserId(1l));
	}
}
