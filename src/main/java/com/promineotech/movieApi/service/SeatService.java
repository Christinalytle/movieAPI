package com.promineotech.movieApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Seat;
import com.promineotech.movieApi.repository.SeatRepository;

@Service 
public class SeatService {
	
	private static final Logger Logger = LogManager.getLogger(SeatService.class); 
	
	@Autowired 
	private SeatRepository repo; 
	
	
	//GET all seats
	public Iterable<Seat> getSeats() {
		return repo.findAll(); 
	}
	
	//POST (create) a seat
	public Seat createSeat(Seat seat)	{
		return repo.save(seat); 
	}
	
	//PUT (update) a seat by its id
	public Seat updateSeat (Seat seat, Long id ) throws Exception {
		try {
			Seat oldSeat = repo.findById(id).orElseThrow(); 
			oldSeat.setSeatNumber(seat.getSeatNumber());
			oldSeat.setRowName(seat.getRowName());
			oldSeat.setSeatPrice(seat.getSeatPrice());
			return repo.save(oldSeat); 
		} catch (Exception e) {
			Logger.error("Exception occured while tryin to update seat " + id, e); 
			throw new Exception("Unable to update product."); 
		}
	}
	
	//DELETE a seat by its id
	public void deleteSeat (Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to delete seat: " + id, e); 
			throw new Exception("Unable to delete product."); 
		}
	}

}
