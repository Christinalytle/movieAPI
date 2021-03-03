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
	private Movie movies; 
	private Auditorium auditoriums; 
	private String time; 
	
	@JsonIgnore
	private Set<Reservation> reservations; 
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getScreeningId() {
		return screeningId;
	}
	public void setScreeningId(Long screeningId) {
		this.screeningId = screeningId;
	}
	

	//Foreign Key movieId
	@ManyToOne
	@JoinColumn(name = "movieId")
	public Movie getMovie() {
		return movies;
	}
	
	public void setMovie(Movie movies) {
		this.movies = movies;
	}
	
	//Foreign Key auditoriumId
	@ManyToOne
	@JoinColumn(name = "auditoriumId")
	public Auditorium getAuditorium() {
		return auditoriums;
	}
	
	public void setAuditorium(Auditorium auditoriums) {
		this.auditoriums = auditoriums;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	//A reservation can have many screenings 
	@OneToMany(mappedBy = "screening")
	public Set<Reservation> getReservation() {
		return reservations;
	}
	
	public void setReservation(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	

}
