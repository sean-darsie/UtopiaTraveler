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
	@Column(name="travelerId")
	private Long travelerId;
	
	@Id
	@Column(name="flightId")
	private Long flightId;
	
	@Id
	@Column(name="bookerId")
	private Long bookerId;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="stripeId")
	private String stripeId;
	
	public Booking(Long travelerId, Long flightId, Long bookerId, boolean active, String stripeId) {
		super();
		this.flightId = flightId;
		this.travelerId = travelerId;
		this.bookerId = bookerId;
		this.active = active;
		this.stripeId = stripeId;
	}
	
	public Booking() {}
	
	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getTravelerId() {
		return travelerId;
	}

	public void setTravelerId(Long travelerId) {
		this.travelerId = travelerId;
	}

	public Long getBookerId() {
		return bookerId;
	}

	public void setBookerId(Long bookerId) {
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
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((bookerId == null) ? 0 : bookerId.hashCode());
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
		result = prime * result + ((stripeId == null) ? 0 : stripeId.hashCode());
		result = prime * result + ((travelerId == null) ? 0 : travelerId.hashCode());
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
		if (bookerId == null) {
			if (other.bookerId != null)
				return false;
		} else if (!bookerId.equals(other.bookerId))
			return false;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		if (stripeId == null) {
			if (other.stripeId != null)
				return false;
		} else if (!stripeId.equals(other.stripeId))
			return false;
		if (travelerId == null) {
			if (other.travelerId != null)
				return false;
		} else if (!travelerId.equals(other.travelerId))
			return false;
		return true;
	}
	
}
