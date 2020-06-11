package com.ss.training.utopia.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_booking")
public class Flight implements Serializable{

	private static final long serialVersionUID = 6602255558264085748L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flightId")
	private Integer flightId;
	
	@Id
	@Column(name="departId")
	private Integer departId;
	
	@Id
	@Column(name="arriveId")
	private Integer arriveId;
	
	@Column(name="departTime")
	private Timestamp departTime;
	
	@Column(name="seatsAvailable")
	private Integer seatsAvailable;
	
	@Column(name="price")
	float price;
	
	
	public Flight(Integer flightId, Integer departId, Integer arriveId, Timestamp departTime, Integer seatsAvailable, float price) {
		super();
		this.flightId = flightId;
		this.departId = departId;
		this.arriveId = arriveId;
		this.departTime = departTime;
		this.seatsAvailable = seatsAvailable;
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		Integer result = 1;
		result = prime * result + arriveId;
		result = prime * result + departId;
		result = prime * result + ((departTime == null) ? 0 : departTime.hashCode());
		result = prime * result + flightId;
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + seatsAvailable;
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
		Flight other = (Flight) obj;
		if (arriveId != other.arriveId)
			return false;
		if (departId != other.departId)
			return false;
		if (departTime == null) {
			if (other.departTime != null)
				return false;
		} else if (!departTime.equals(other.departTime))
			return false;
		if (flightId != other.flightId)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (seatsAvailable != other.seatsAvailable)
			return false;
		return true;
	}
	
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public Integer getArriveId() {
		return arriveId;
	}
	public void setArriveId(Integer arriveId) {
		this.arriveId = arriveId;
	}
	public Timestamp getDepartTime() {
		return departTime;
	}
	public void setDepartTime(Timestamp departTime) {
		this.departTime = departTime;
	}
	public Integer getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(Integer seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
