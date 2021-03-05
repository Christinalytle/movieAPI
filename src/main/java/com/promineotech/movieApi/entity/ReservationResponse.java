package com.promineotech.movieApi.entity;

import java.util.Set;

public class ReservationResponse {
	
	Long reservationId; 
	String customerEmail; 
	Set<SeatResponse> seats; 
	Long screeningId; 
	double price; 
	
	
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Set<SeatResponse> getSeats() {
		return seats;
	}
	public void setSeats(Set<SeatResponse> seats) {
		this.seats = seats;
	}
	public Long getScreeningId() {
		return screeningId;
	}
	public void setScreeningId(Long screeningId) {
		this.screeningId = screeningId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
