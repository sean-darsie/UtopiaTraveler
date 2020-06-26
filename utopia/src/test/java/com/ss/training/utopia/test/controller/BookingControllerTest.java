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

import com.ss.training.utopia.controller.BookingController;
import com.ss.training.utopia.controller.UserController;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.User;
import com.ss.training.utopia.service.BookingService;
import com.ss.training.utopia.service.UserService;
import com.stripe.exception.StripeException;

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
	}
}