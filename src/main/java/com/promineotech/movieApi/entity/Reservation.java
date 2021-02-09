package com.promineotech.movieApi.entity;

import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Reservation {
	
	private Long reservationId; 
	private Customer customer; 
	private Screening screening; 
	private Set<Seat> seats;
	private double reservationAmount; 
	
	
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	
	@ManyToOne
	@JoinColumn (name = "screeningId")
	public Screening getScreening() {
		return screening;
	}
	public void setScreening(Screening screening) {
		this.screening = screening;
	}
	
	@ManyToMany(mappedBy = "reservation")
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
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	} 
	
	

}
