package com.promineotech.movieApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.movieApi.entity.Seat;
import com.promineotech.movieApi.service.SeatService;

@RestController 
@RequestMapping("/seats")
public class SeatController {
	
	@Autowired
	private SeatService service; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getSeats() {
		return new ResponseEntity<Object>(service.getSeats(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createSeat (@RequestBody Seat seat) {
		return new ResponseEntity<Object>(service.createSeat(seat), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateSeat (@RequestBody Seat seat, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateSeat(seat, id), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update product.", HttpStatus.BAD_REQUEST); 
		}
	}

}
