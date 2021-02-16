package com.promineotech.movieApi.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
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
	
	//logger 
	
	@Autowired
	private ReservationRepository resRepo; 
	
	//@Autowired
	//private ScreeningRepository screeningRepo; 
	
	@Autowired
	private SeatRepository seatRepo; 
	
	@Autowired 
	private CustomerRepository customerRepo;
	
	public Iterable<Reservation> getReservation() {
		return resRepo.findAll(); 
	}
	
	public Reservation createReservation (Screening screeningId, Set<Long> seatIds, Long customerId) throws Exception {
		try {
			Customer customer = customerRepo.findById(customerId).orElseThrow(); 
			Reservation reservation = startReservation(seatIds, screeningId, customer); 
			return resRepo.save(reservation); 
		} catch (Exception e) {
			//logger 
			throw new Exception("Unable to update order."); 
		}
		
	}

	private Reservation startReservation(Set<Long> seatIds, Screening screeningId, Customer customer) {
		Reservation reservation = new Reservation(); 
		reservation.setCustomer(customer);
		reservation.setSeats(convertToSeatSet(seatRepo.findAllById(seatIds)));
		reservation.setScreening(screeningId);
		reservation.setReservationAmount(calculateReservationTotal(reservation.getSeats()));
		addSeatsToReservation(reservation); 
		
		return reservation;
	}
	
//	public Reservation updateReservation()

	private Set<Seat> convertToSeatSet(Iterable<Seat> iterable) {
		Set<Seat> set = new HashSet<Seat>(); 
		for (Seat seat : iterable) {
			set.add(seat); 
		}
		
		return set; 
	}
	
	private void addSeatsToReservation(Reservation reservation) {
		Set<Seat> seats = reservation.getSeats(); 
		for (Seat seat : seats) {
			seat.getReservations().add(reservation); 
		}
	}

	private double calculateReservationTotal(Set<Seat> seats) {
		double total = 0; 
		for (Seat seat : seats) {
			total += seat.getSeatPrice();
		}
		return total; 
	}

}
