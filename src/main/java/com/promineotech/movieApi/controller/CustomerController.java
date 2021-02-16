package com.promineotech.movieApi.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.movieApi.entity.Credentials;
import com.promineotech.movieApi.service.AuthService;

@RestController 
@RequestMapping("/customers")
public class CustomerController {
	
	
	@Autowired 
	private AuthService authService; 
	
	@RequestMapping (value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Object> register (@RequestBody Credentials cred) {
		try {
			return new ResponseEntity<Object>(authService.register(cred), HttpStatus.CREATED); 
			
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST); 
		}
	}
	
	
	@RequestMapping (value = "/login", method=RequestMethod.POST)
	public ResponseEntity<Object> logIn(@RequestBody Credentials cred) {
		try {
			return new ResponseEntity<Object>(authService.login(cred), HttpStatus.OK); 
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	

}
