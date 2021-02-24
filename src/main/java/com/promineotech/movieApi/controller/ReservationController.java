package com.promineotech.movieApi.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.movieApi.entity.Reservation;
import com.promineotech.movieApi.entity.Screening;
import com.promineotech.movieApi.service.ReservationService;

@RestController 
@RequestMapping("/customers/{id}/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService service; 
	
	@RequestMapping (method = RequestMethod.POST)
	public ResponseEntity<Object> createReservation(@RequestBody Reservation reservation, @PathVariable Long id ) {
			return new ResponseEntity<Object>(service.createReservation(reservation, id), HttpStatus.CREATED); 
	}
	
	
	

}