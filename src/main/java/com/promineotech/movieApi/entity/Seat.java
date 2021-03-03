package com.promineotech.movieApi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Seat {
	
	private Long seatId; 
	private Long seatNumber; 
	private double seatPrice; 
	private String rowName; 
	
	private Auditorium auditorium;
	
	private Set<Reservation> reservations; 
	
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getSeatId() {
		return seatId;
	}
	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}
	
	public Long getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Long seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	
	public double getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(double seatPrice) {
		this.seatPrice = seatPrice;
	}
	
	//Foreign Key auditoriumID
	@ManyToOne
	@JoinColumn(name = "auditoriumId")
	public Auditorium getAuditorium() {
		return auditorium;
	}
	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}
	
	/*This ManyToMany table is used to connect a set of seats to a reservation number. This will 
	 * allow customers to pick a set of seats that will connect to their reservation ID. */
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable (name = "seats_reserved",
				joinColumns = @JoinColumn(name = "seatId", referencedColumnName="seatId"), 
				inverseJoinColumns = @JoinColumn(name = "reservationId", referencedColumnName ="reservationId"))
	public Set<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
