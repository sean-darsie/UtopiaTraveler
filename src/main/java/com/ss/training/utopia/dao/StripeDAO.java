package com.ss.training.utopia.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ss.training.utopia.entity.Flight;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import com.stripe.param.ChargeCreateParams;

@Component
public class StripeDAO {
	public String purchaseFlight(String token, Flight flight) throws StripeException {
		
		Stripe.apiKey = System.getenv("STRIPE_KEY");
		Charge charge;
		
		ChargeCreateParams params =
				  ChargeCreateParams.builder()
				    .setAmount(100l)
				    .setCurrency("usd")
				    .setDescription("Example charge")
				    .setSource(token)
				    .build();
		
		charge = Charge.create(params);

		return charge.getId();
	}
	
	public void refundFlight(String stripeId) throws StripeException {
		Stripe.apiKey = System.getenv("STRIPE_KEY");

		Map<String, Object> params = new HashMap<>();
		params.put(
		  "charge",
		  stripeId
		);

		Refund refund = Refund.create(params);
	}
}
