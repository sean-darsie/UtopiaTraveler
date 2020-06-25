package com.ss.training.utopia.test.controller;

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

import com.ss.training.utopia.controller.AirportController;
import com.ss.training.utopia.entity.Airport;
import com.ss.training.utopia.service.AirportService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AirportControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	private AirportService airportService;
	
	@InjectMocks
	private AirportController airportController;

	@BeforeAll
	public void setup() throws Exception
	{
		mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
	}
	
	@Test
	public void testGetAirports() throws Exception {
		// create mock data to return;
		List<Airport> listAirports = new ArrayList<Airport>();
		listAirports.add(new Airport(1l,"departure"));
		listAirports.add(new Airport(2l,"arrival"));
		Airport[] mockAirports = listAirports.toArray(new Airport[listAirports.size()]);
		
		JSONArray airportArray = new JSONArray();
		
		JSONObject airport1 = new JSONObject();
		airport1.put("airportId", 1l);
		airport1.put("name", "departure");
		
		JSONObject airport2 = new JSONObject();
		airport2.put("airportId", 2l);
		airport2.put("name", "arrival");
		
		airportArray.add(airport1);
		airportArray.add(airport2);
		
		Mockito.when(airportService.readAllAirports()).thenReturn(mockAirports);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/traveler/airports")
				  	.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().json(airportArray.toString()));
		
		Mockito.when(airportService.readAllAirports()).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/traveler/airports")
			  	.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
