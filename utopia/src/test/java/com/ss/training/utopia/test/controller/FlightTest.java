package com.ss.training.utopia.test.controller;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

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

import com.ss.training.utopia.controller.FlightController;
import com.ss.training.utopia.entity.Flight;
import com.ss.training.utopia.service.FlightService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class FlightTest {
	private MockMvc mockMvc;
	
	@Mock
	private FlightService flightService;
	
	@InjectMocks
	private FlightController flightController;

	@BeforeAll
	public void setup() throws Exception
	{
		mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
	}
	
	@Test
	public void getBookableFlights() throws Exception {
		// declare mock data. 
		List<Flight> flightList =  new ArrayList<Flight>();
		Timestamp time = new Timestamp(1592971895172l);
		flightList.add(new Flight(1l,2l,time, 20,100f,1l));
		Flight[] mockFlights = flightList.toArray(new Flight[flightList.size()]);
		
		JSONArray responseArray = new JSONArray();
		JSONObject mockResponse = new JSONObject(); 
		mockResponse.put("departId", 1l);
		mockResponse.put("arriveId", 2l);
		mockResponse.put("departTime", 1592971895172l);
		mockResponse.put("seatsAvailable", 20);
		mockResponse.put("price", 100f);
		mockResponse.put("flightId", 1l);
		responseArray.add(mockResponse);		
		
		Mockito.when(flightService.getBookableFlights(1l, 2l, 1l)).thenReturn(mockFlights);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/traveler/travelers/1/flights/departure/1/arrival/2")
			  	.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(responseArray.toString()));
	
		Mockito.when(flightService.getBookableFlights(1l, 2l, 1l)).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/traveler/travelers/1/flights/departure/1/arrival/2")
			  	.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError());
		
		Mockito.when(flightService.getBookableFlights(1l, 2l, 1l)).thenReturn(new Flight[0]);
		mockMvc.perform(MockMvcRequestBuilders.get("/traveler/travelers/1/flights/departure/1/arrival/2")
			  	.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}
	
}
