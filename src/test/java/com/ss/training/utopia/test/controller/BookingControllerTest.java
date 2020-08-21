package com.ss.training.utopia.test.controller;

import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.ArrayList;
import java.util.List;

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

import com.ss.training.utopia.controller.BookingController;
import com.ss.training.utopia.controller.UserController;
import com.ss.training.utopia.entity.Airport;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.service.BookingService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class BookingControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	private BookingService bookingService;
	
	@InjectMocks
	private BookingController bookingController;

	@BeforeAll
	public void setup() throws Exception
	{
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
	}
	
	@Test
	public void purchaseTicketTest() throws Exception {
		// create mock purchase
		JSONObject mockBooking = new JSONObject(); 
		mockBooking.put("flightId", 1l);
		mockBooking.put("bookerId", 1l);
		mockBooking.put("travelerId", 1l);
		mockBooking.put("active", true);
		mockBooking.put("stripeId", "token");
		
		Booking fakeBooking = new Booking(1l, 1l, 1l, true, "token");
		
		Mockito.when(bookingService.purchaseFlight(1l, 1l, 1l, "token")).thenReturn(fakeBooking);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/traveler/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockBooking.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.content().json(mockBooking.toString()));

		Mockito.when(bookingService.purchaseFlight(1l, 1l, 1l, "token")).thenThrow(new RuntimeException("error message"));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/traveler/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockBooking.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
	
	@Test
	public void refundFlight() throws Exception {
		// create mock purchase
		JSONObject mockBooking = new JSONObject(); 
		mockBooking.put("flightId", 1l);
		mockBooking.put("bookerId", 1l);
		mockBooking.put("travelerId", 1l);
		mockBooking.put("active", true);
		mockBooking.put("stripeId", "token");
		
		Booking fakeBooking = new Booking(1l, 1l, 1l, true, "token");

		Mockito.when(bookingService.cancelFlight(fakeBooking)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockBooking.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isNoContent());

		Mockito.when(bookingService.cancelFlight(fakeBooking)).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockBooking.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
		
		Mockito.when(bookingService.cancelFlight(fakeBooking)).thenThrow(new RuntimeException("error message"));
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockBooking.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());

	}
	
	@Test
	public void getBookingsByUserSuccess() throws Exception {
		List<Booking> listBookings = new ArrayList<Booking>();
		listBookings.add(new Booking(1l,1l, 1l, true, "token"));
		Booking[] bookings = listBookings.toArray(new Booking[listBookings.size()]);
		
		JSONArray bookingArray = new JSONArray();

		JSONObject mockBooking = new JSONObject(); 
		mockBooking.put("flightId", 1l);
		mockBooking.put("bookerId", 1l);
		mockBooking.put("travelerId", 1l);
		mockBooking.put("active", true);
		mockBooking.put("stripeId", "token");
		
		bookingArray.add(mockBooking);
		
		Mockito.when(bookingService.readActiveBookingByTravelerId(1l)).thenReturn(bookings);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/bookings/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockBooking.toJSONString()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json(bookingArray.toString()));
	}
	
	@Test
	public void getBookingsByUserFaileScenario() throws Exception {
		Mockito.when(bookingService.readActiveBookingByTravelerId(1l)).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/bookings/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
	
	@Test
	public void getBookingsByUserNoBookings() throws Exception {
		Mockito.when(bookingService.readActiveBookingByTravelerId(1l)).thenReturn(new Booking[0]);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/traveler/bookings/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent());

	}
}
