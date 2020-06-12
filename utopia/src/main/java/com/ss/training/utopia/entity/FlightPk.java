package com.ss.training.utopia.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class FlightPk implements Serializable {
	
	private static final long serialVersionUID = 1297844420977268568L;
	
	Integer departId;
	Integer arriveId;
	Timestamp departTme;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arriveId == null) ? 0 : arriveId.hashCode());
		result = prime * result + ((departId == null) ? 0 : departId.hashCode());
		result = prime * result + ((departTme == null) ? 0 : departTme.hashCode());
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
		FlightPk other = (FlightPk) obj;
		if (arriveId == null) {
			if (other.arriveId != null)
				return false;
		} else if (!arriveId.equals(other.arriveId))
			return false;
		if (departId == null) {
			if (other.departId != null)
				return false;
		} else if (!departId.equals(other.departId))
			return false;
		if (departTme == null) {
			if (other.departTme != null)
				return false;
		} else if (!departTme.equals(other.departTme))
			return false;
		return true;
	}

	
}
