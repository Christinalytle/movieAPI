package com.promineotech.movieApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.movieApi.entity.ReservationDto;
import com.promineotech.movieApi.service.ReservationService;

@RestController 
@RequestMapping("/customers/{customerId}/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService service; 
	
	@RequestMapping (method = RequestMethod.POST)
	public ResponseEntity<Object> startReservation(@RequestBody ReservationDto res, @PathVariable Long customerId){
		try {	
				return new ResponseEntity<Object>(service.startReservation(res.getSeatIds(), res.getScreeningId(), customerId), 
					HttpStatus.CREATED); 
	} catch (Exception e) {
		return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST); 
	}
	}
	
	
	

}