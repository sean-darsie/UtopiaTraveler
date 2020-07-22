package com.ss.training.utopia.test.controller;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ss.training.utopia.controller.UserController;
import com.ss.training.utopia.entity.User;
import com.ss.training.utopia.service.UserService;

import net.minidev.json.JSONObject;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;

	@BeforeAll
	public void setup() throws Exception
	{
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void createUserTest() throws Exception {
		// create mock user
//		User mockUser = new User(null, "username", "name","password", "role");
		JSONObject mockUser = new JSONObject();
		mockUser.put("username", "username");
		mockUser.put("name", "name");
		mockUser.put("password", "password");
		mockUser.put("role", "TRAVELER");
		
		User user = new User(null, "username", "name", "password", "TRAVELER");
		
		Mockito.when(userService.createUser(user)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/traveler/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockUser.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isCreated());

		Mockito.when(userService.createUser(user)).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/traveler/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockUser.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updateUserTest() throws Exception {
		// create mock user
		JSONObject mockUser = new JSONObject();
		mockUser.put("userId", 1l);
		mockUser.put("username", "username");
		mockUser.put("name", "name");
		mockUser.put("password", "password");
		mockUser.put("role", "TRAVELER");
		
		User user = new User(1l, "username", "name", "password", "TRAVELER");
		
		Mockito.when(userService.updateUser(user)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockUser.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isNoContent());

		Mockito.when(userService.updateUser(user)).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockUser.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	
}
