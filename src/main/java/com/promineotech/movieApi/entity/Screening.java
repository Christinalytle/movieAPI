package com.promineotech.movieApi.entity;




import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Id; 
import javax.persistence.GeneratedValue; 

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Screening {
	
	private Long screeningId; 
	private Movie movie; 
	private Auditorium auditorium; 
	private String time; 
	
	@JsonIgnore
	private Set<Reservation> reservation; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getScreeningId() {
		return screeningId;
	}
	public void setScreeningId(Long screeningId) {
		this.screeningId = screeningId;
	}
	
	@ManyToOne
	@JoinColumn(name = "movieId")
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	@ManyToOne
	@JoinColumn(name = "auditoriumId")
	public Auditorium getAuditorium() {
		return auditorium;
	}
	
	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@OneToMany(mappedBy = "screening")
	public Set<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}

}
