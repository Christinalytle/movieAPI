package com.promineotech.movieApi.entity;

import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Customer {
	
	private Long customerId; 
	private String hash; //password 
	private String email;
	
	@JsonIgnore
	private Set<Reservation> reservation; 
	
		
		
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

	@OneToMany(mappedBy = "customer")
	public Set<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}


	
	}


