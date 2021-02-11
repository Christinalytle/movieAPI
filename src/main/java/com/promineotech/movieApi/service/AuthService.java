package com.promineotech.movieApi.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Credentials;
import com.promineotech.movieApi.entity.Customer;
import com.promineotech.movieApi.repository.CustomerRepository;

@Service
public class AuthService {
	
	@Autowired
	private CustomerRepository customerRepo; 
	
	public Customer register(Credentials cred, String email) throws AuthenticationException {
		Customer customer = new Customer(); 
		customer.setUsername(cred.getUsername());
		customer.setHash(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		customer.setEmail(email); 
		try {
			customerRepo.save(customer); 
			return customer;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException ("Username not available."); 
		}
		
	}
	
	public Customer login (Credentials cred) throws AuthenticationException {
		Customer foundCustomer = customerRepo.findByUserName(cred.getUsername()); 
		if (foundCustomer != null && BCrypt.checkpw(cred.getPassword(), foundCustomer.getHash())) {
			return foundCustomer; 
		}
		throw new AuthenticationException("Incorrect username and password"); 
	}
	
	
	

}
