package com.promineotech.movieApi.entity;

public class Customer {
	
	private Long customerId; 
	private String hash; //password 
	private String username; 
	private String email;
		
		
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
	
	public String getUsername() {
			return username;
		}
	
	public void setUsername(String username) {
			this.username = username;
		}
	
	public String getEmail() {
			return email;
		}
	
	public void setEmail(String email) {
			this.email = email;
		}

	

	
	
	
	}


