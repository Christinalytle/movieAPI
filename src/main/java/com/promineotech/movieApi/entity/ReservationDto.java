package com.promineotech.movieApi.entity;

import java.util.Set;

public class ReservationDto {
	
	//The DTO is used to input variables in PostMan so they can connect to the right object 
	
	private Long customerId; 
	private Long screeningId; 
	private Set<Long> seatIds; 
	private double reservationAmount;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getScreeningId() {
		return screeningId;
	}
	public void setScreeningId(Long screeningId) {
		this.screeningId = screeningId;
	}
	
	public Set<Long> getSeatIds() {
		return seatIds;
	}
	public void setSeatIds(Set<Long> seatIds) {
		this.seatIds = seatIds;
	}
	
	public double getReservationAmount() {
		return reservationAmount;
	}
	public void setReservationAmount(double reservationAmount) {
		this.reservationAmount = reservationAmount;
	} 
	

}
