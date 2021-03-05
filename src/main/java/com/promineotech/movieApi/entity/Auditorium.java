package com.promineotech.movieApi.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Auditorium {
	
	private Long auditoriumId; 
	private Long auditoriumNumber;
	
	@JsonIgnore
	private Set<Seat> seats; 
	
	@JsonIgnore
	private Set<Screening> screenings;  
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getAuditoriumId() {
		return auditoriumId;
	}
	
	public void setAuditoriumId(Long auditoriumId) {
		this.auditoriumId = auditoriumId;
	}
	
	public Long getAuditoriumNumber() {
		return auditoriumNumber;
	}
	
	public void setAuditoriumNumber(Long auditoriumNumber) {
		this.auditoriumNumber = auditoriumNumber;
	}

	//Foreign Key is seatId
	@OneToMany (mappedBy = "auditorium")
	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	//Foreign Key is screeningId
	@OneToMany (mappedBy = "auditorium")
	public Set<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(Set<Screening> screenings) {
		this.screenings = screenings;
	}

}
