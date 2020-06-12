package com.ss.training.utopia.entity;

import java.io.Serializable;

public class BookingPk implements Serializable {
	
	private static final long serialVersionUID = 1125044906555366554L;
	
	Integer flightId;
	Integer travelerId;
	Integer bookerId;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookerId == null) ? 0 : bookerId.hashCode());
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
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
		BookingPk other = (BookingPk) obj;
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
		if (travelerId == null) {
			if (other.travelerId != null)
				return false;
		} else if (!travelerId.equals(other.travelerId))
			return false;
		return true;
	}
	
}
