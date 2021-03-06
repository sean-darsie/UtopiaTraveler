package com.ss.training.utopia.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user")
public class User implements Serializable {

	private static final long serialVersionUID = 4726738045595926757L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	Long userId;
	
	
	@Column(name="username")
	String username;
	
	@Column(name="name")
	String name;
	
	@Column(name="password")
	String password;
	
	@Column(name="role")
	String role;
	
	@OneToMany
	@JoinColumn(name="travelerId", referencedColumnName="userId")
	List<Booking> travelersFlights;
	
	@OneToMany
	@JoinColumn(name="bookerId", referencedColumnName="userId")
	List<Booking> usersBookings;
	
	public User(Long userId, String username, String name, String password, String role, List<Booking> travelersFlights,
			List<Booking> usersBookings) {
		super();
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.password = password;
		this.role = role;
		this.travelersFlights = travelersFlights;
		this.usersBookings = usersBookings;
	}

	public List<Booking> getTravelersFlights() {
		return travelersFlights;
	}

	public void setTravelersFlights(List<Booking> travelersFlights) {
		this.travelersFlights = travelersFlights;
	}

	public List<Booking> getUsersBookings() {
		return usersBookings;
	}

	public void setUsersBookings(List<Booking> usersBookings) {
		this.usersBookings = usersBookings;
	}

	public User(Long userId, String username, String name, String password, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public User() {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((travelersFlights == null) ? 0 : travelersFlights.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((usersBookings == null) ? 0 : usersBookings.hashCode());
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
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (travelersFlights == null) {
			if (other.travelersFlights != null)
				return false;
		} else if (!travelersFlights.equals(other.travelersFlights))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (usersBookings == null) {
			if (other.usersBookings != null)
				return false;
		} else if (!usersBookings.equals(other.usersBookings))
			return false;
		return true;
	}
	
	
}
