package com.ss.training.utopia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Transient;


@Entity
@Table(name="tbl_airport")
public class Airport implements Serializable {

	private static final long serialVersionUID = -9142309234608437181L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="airportId")
	private Integer airportId;
	
	@Column(name="name")
	private String name;

	
	public Airport(Integer airportId, String name) {
		super();
		this.airportId = airportId;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		Integer result = 1;
		result = prime * result + airportId;
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
		if (airportId != other.airportId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public Integer getAirportId() {
		return airportId;
	}
	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
	}
	public String getCity() {
		return name;
	}
	public void setCity(String name) {
		this.name = name;
	}

}
