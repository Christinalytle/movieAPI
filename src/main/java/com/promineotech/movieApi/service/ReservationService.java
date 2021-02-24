package com.promineotech.movieApi.service;

import java.util.HashSet;
import java.util.Set;

import javax.naming.AuthenticationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Customer;
import com.promineotech.movieApi.entity.Reservation;
import com.promineotech.movieApi.entity.Screening;
import com.promineotech.movieApi.entity.Seat;
import com.promineotech.movieApi.repository.CustomerRepository;
import com.promineotech.movieApi.repository.ReservationRepository;
import com.promineotech.movieApi.repository.ScreeningRepository;
import com.promineotech.movieApi.repository.SeatRepository;

@Service 
public class ReservationService {
	
	private static final Logger Logger = LogManager.getLogger(ReservationService.class); 
	
	@Autowired
	private ReservationRepository resRepo; 
	
	@Autowired
	private ScreeningRepository screeningRepo; 
	
	@Autowired
	private SeatRepository seatRepo; 
	
	@Autowired 
	private CustomerRepository customerRepo;
	
	
	public Iterable<Reservation> getReservation() {
		return resRepo.findAll(); 
	}
	
	public Reservation createReservation (Set<Long> seatIds, Long customerId, Long screeningId) throws AuthenticationException {
		try {
			Customer customer = customerRepo.findById(customerId).orElseThrow(); 
			Screening screening = screeningRepo.findById(screeningId).orElseThrow(); 
			Reservation reservation = startReservation(seatIds, customer, screening); 
			return resRepo.save(reservation); 
		} catch (DataIntegrityViolationException e) {
			Logger.error("Exception occured while trying to create a screening");  
			throw new AuthenticationException("Seats not available"); 
		}
		
	}

	private Reservation startReservation(Set<Long> seatIds, Customer customer, Screening screening) {
		Reservation reservation = new Reservation(); 
		reservation.setCustomer(customer);
		reservation.setSeats(convertToSeatSet(seatRepo.findAllById(seatIds)));
		reservation.setScreening(screening); 
		reservation.setReservationAmount(calculateReservationTotal(reservation.getSeats()));
		addSeatsToReservation(reservation); 

		return reservation;
	}
	
//	public Reservation updateReservation()

	private void addSeatsToReservation(Reservation reservation) {
		Set<Seat> seats = reservation.getSeats(); 
		for (Seat seat : seats) {
			seat.getReservations().add(reservation); 
		}
	}
	
	private Set<Seat> convertToSeatSet(Iterable<Seat> iterable) {
		Set<Seat> set = new HashSet<Seat>(); 
		for (Seat seat : iterable) {
			set.add(seat); 
		}
		
		return set; 
	}
	

	private double calculateReservationTotal(Set<Seat> seats) {
		double total = 0; 
		for (Seat seat : seats) {
			total += seat.getSeatPrice();
		}
		return total; 
	}
	
	

}