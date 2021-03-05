package com.promineotech.movieApi.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Reservation {
	
	
	private Long reservationId; 
	
	
	private Customer customer; 
	
	
	private Screening screenings; 
	
	
	private Set<Seat> seats;
	
	private double reservationAmount; 
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	
	//Foreign Key screeningId
	@ManyToOne
	@JoinColumn (name = "screeningId") 
	public Screening getScreening() {
		return screenings;
	}

	public void setScreening(Screening screenings) {
		this.screenings = screenings;
	}
	
	//Foreign Key a set of seatIds
	@ManyToMany(mappedBy = "reservations")
	public Set<Seat> getSeats() {
		return seats;
	}
	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	
	public double getReservationAmount() {
		return reservationAmount;
	}
	public void setReservationAmount(double reservationAmount) {
		this.reservationAmount = reservationAmount;
	}
	
	//Foreign key customerId
	@ManyToOne
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	} 
	
	

}
