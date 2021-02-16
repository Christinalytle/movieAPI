package com.promineotech.movieApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.movieApi.entity.Customer;


public interface CustomerRepository extends JpaRepository <Customer, Long> {
	
	public Customer findByEmail(String email); 

}
