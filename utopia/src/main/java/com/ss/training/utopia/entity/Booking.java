package com.ss.training.utopia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.IdClass;


@Entity
@Table(name="tbl_booking")
@IdClass(BookingPk.class)
public class Booking implements Serializable{

	private static final long serialVersionUID = 746930832725324533L;
	
	@Id
	@Column(name="flightId")
	private Integer flightId;
	
	@Id
	@Column(name="travelerId")
	private Integer travelerId;
	
	@Id
	@Column(name="bookerId")
	private Integer bookerId;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="stripeId")
	private String stripeId;
	
	public Booking(Integer flightId, Integer travelerId, Integer bookerId, boolean active, String stripeId) {
		super();
		this.flightId = flightId;
		this.travelerId = travelerId;
		this.bookerId = bookerId;
		this.active = active;
		this.stripeId = stripeId;
	}
	
	public Booking() {}
	
	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public Integer getTravelerId() {
		return travelerId;
	}

	public void setTravelerId(Integer travelerId) {
		this.travelerId = travelerId;
	}

	public Integer getBookerId() {
		return bookerId;
	}

	public void setBookerId(Integer bookerId) {
		this.bookerId = bookerId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStripeId() {
		return stripeId;
	}

	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		Integer result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + bookerId;
		result = prime * result + flightId;
		result = prime * result + ((stripeId == null) ? 0 : stripeId.hashCode());
		result = prime * result + travelerId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (active != other.active)
			return false;
		if (bookerId != other.bookerId)
			return false;
		if (flightId != other.flightId)
			return false;
		if (stripeId == null) {
			if (other.stripeId != null)
				return false;
		} else if (!stripeId.equals(other.stripeId))
			return false;
		if (travelerId != other.travelerId)
			return false;
		return true;
	}
	
}
