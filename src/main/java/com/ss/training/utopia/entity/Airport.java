package com.ss.training.utopia.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="tbl_airport")
public class Airport implements Serializable {

	private static final long serialVersionUID = -9142309234608437181L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="airportId")
	private Long airportId;
	
	@Column(name="name")
	private String name;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="departId")
	List<Flight> flightsFrom;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="arriveId")
	List<Flight> flightsTo;
	
	public Airport(Long airportId, String name, List<Flight> flightsFrom, List<Flight> flightsTo) {
		super();
		this.airportId = airportId;
		this.name = name;
		this.flightsFrom = flightsFrom;
		this.flightsTo = flightsTo;
	}

	public List<Flight> getFlightsFrom() {
		return flightsFrom;
	}

	public void setFlightsFrom(List<Flight> flightsFrom) {
		this.flightsFrom = flightsFrom;
	}

	public List<Flight> getFlightsTo() {
		return flightsTo;
	}

	public void setFlightsTo(List<Flight> flightsTo) {
		this.flightsTo = flightsTo;
	}
	
	
	public Airport(Long airportId, String name) {
		super();
		this.airportId = airportId;
		this.name = name;
	}
	
	public Airport() {
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportId == null) ? 0 : airportId.hashCode());
		result = prime * result + ((flightsFrom == null) ? 0 : flightsFrom.hashCode());
		result = prime * result + ((flightsTo == null) ? 0 : flightsTo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Airport other = (Airport) obj;
		if (airportId == null) {
			if (other.airportId != null)
				return false;
		} else if (!airportId.equals(other.airportId))
			return false;
		if (flightsFrom == null) {
			if (other.flightsFrom != null)
				return false;
		} else if (!flightsFrom.equals(other.flightsFrom))
			return false;
		if (flightsTo == null) {
			if (other.flightsTo != null)
				return false;
		} else if (!flightsTo.equals(other.flightsTo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public Long getAirportId() {
		return airportId;
	}
	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
