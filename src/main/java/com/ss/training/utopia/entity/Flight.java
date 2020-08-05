package com.ss.training.utopia.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_flight")
@IdClass(FlightPk.class)
public class Flight implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8369948594735927043L;
	
	@Id
	@Column
	Long departId;
	
	@Id
	@Column
	Long arriveId;
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "departId", referencedColumnName = "airportId",insertable=false, updatable=false)
	private Airport departAirport;

	@Id
	@ManyToOne
	@JoinColumn(name = "arriveId", referencedColumnName = "airportId",insertable=false, updatable=false)
	private Airport arriveAirport;
	
	@Id
	@Column
	Timestamp departTime;
	
	@Column
	Integer seatsAvailable;
	
	@Column
	Float price;
	
	
	@Column(unique = true)
	private Long flightId;
	
	
	
	public Integer getSeatsAvailable() {
		return seatsAvailable;
	}

	public Airport getDepartAirport() {
		return departAirport;
	}

	public void setDepartAirport(Airport departAirport) {
		this.departAirport = departAirport;
	}

	public Airport getArriveAirport() {
		return arriveAirport;
	}

	public void setArriveAirport(Airport arriveAirport) {
		this.arriveAirport = arriveAirport;
	}

	public Flight(Long departId, Long arriveId, Airport departAirport, Airport arriveAirport, Timestamp departTime,
			Integer seatsAvailable, Float price, Long flightId) {
		super();
		this.departId = departId;
		this.arriveId = arriveId;
		this.departAirport = departAirport;
		this.arriveAirport = arriveAirport;
		this.departTime = departTime;
		this.seatsAvailable = seatsAvailable;
		this.price = price;
		this.flightId = flightId;
	}

	public void setSeatsAvailable(Integer seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public Long getArriveId() {
		return arriveId;
	}

	public void setArriveId(Long arriveId) {
		this.arriveId = arriveId;
	}

	public Timestamp getDepartTime() {
		return departTime;
	}

	public void setDepartTime(Timestamp departTime) {
		this.departTime = departTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arriveAirport == null) ? 0 : arriveAirport.hashCode());
		result = prime * result + ((arriveId == null) ? 0 : arriveId.hashCode());
		result = prime * result + ((departAirport == null) ? 0 : departAirport.hashCode());
		result = prime * result + ((departId == null) ? 0 : departId.hashCode());
		result = prime * result + ((departTime == null) ? 0 : departTime.hashCode());
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((seatsAvailable == null) ? 0 : seatsAvailable.hashCode());
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
		if (arriveAirport == null) {
			if (other.arriveAirport != null)
				return false;
		} else if (!arriveAirport.equals(other.arriveAirport))
			return false;
		if (arriveId == null) {
			if (other.arriveId != null)
				return false;
		} else if (!arriveId.equals(other.arriveId))
			return false;
		if (departAirport == null) {
			if (other.departAirport != null)
				return false;
		} else if (!departAirport.equals(other.departAirport))
			return false;
		if (departId == null) {
			if (other.departId != null)
				return false;
		} else if (!departId.equals(other.departId))
			return false;
		if (departTime == null) {
			if (other.departTime != null)
				return false;
		} else if (!departTime.equals(other.departTime))
			return false;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (seatsAvailable == null) {
			if (other.seatsAvailable != null)
				return false;
		} else if (!seatsAvailable.equals(other.seatsAvailable))
			return false;
		return true;
	}

	/**
	 * 
	 * @param departId
	 * @param arriveId
	 * @param departTime
	 * @param seatsAvailable
	 * @param price
	 * @param flightId
	 */
	public Flight(Long departId, Long arriveId, Timestamp departTime, Integer seatsAvailable, Float price,
			Long flightId) {
		super();
		this.departId = departId;
		this.arriveId = arriveId;
		this.departTime = departTime;
		this.seatsAvailable = seatsAvailable;
		this.price = price;
		this.flightId = flightId;
	}

	public Flight() {}

}
