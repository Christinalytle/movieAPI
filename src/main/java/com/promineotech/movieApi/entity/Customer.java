package com.promineotech.movieApi.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Customer {
	
	private Long customerId; 
	private String hash; //password 
	private String email;
	
	private Screening screening; 
	
	private Set<Seat> seats; 
		
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	} 
	
	public String getHash() {
			return hash;
		}
		
	public void setHash(String hash) {
			this.hash = hash;
		}
	
	public String getEmail() {
			return email;
		}
	
	public void setEmail(String email) {
			this.email = email;
		}

	@ManyToOne
	@JoinColumn(name = "screeningId")
	public Screening getScreening() {
		return screening;
	}

	@ManyToMany(mappedBy = "customer")
	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	
	}


