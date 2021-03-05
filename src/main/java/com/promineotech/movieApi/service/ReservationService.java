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
import com.promineotech.movieApi.entity.ReservationResponse;
import com.promineotech.movieApi.entity.Screening;
import com.promineotech.movieApi.entity.Seat;
import com.promineotech.movieApi.entity.SeatResponse;
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
	
	
	//GET all reservations 
	public Iterable<Reservation> getReservation() {
		return resRepo.findAll(); 
	}
	
	//GET reservation by its id
	public Reservation getReservation (Long id) {
		return resRepo.findById(id).orElseThrow(); 
	}

	//POST (create) a reservation. 
	//Find the screening number and seat numbers 
	//Find the customerId from the URL
	//Get the total amount by looping thru the seats and adding their prices together 
	//Add the seats to the reservation to satisfy the ManyToMany table 
	//Save the reservation 
	//Convert reservation to a ReservationResponse (to keep it from doing an infinite recursion) 
	//Return a ResrvationResponse 
	public ReservationResponse startReservation(Set<Long> seatIds, Long screeningId , Long customerId) throws AuthenticationException {
		try {
			Reservation reservation = new Reservation(); 
			Screening screen = screeningRepo.findById(screeningId).orElseThrow(); 
			Customer customer = customerRepo.findById(customerId).orElseThrow();
			reservation.setSeats(convertToSeatSet(seatRepo.findAllById(seatIds)));
			reservation.setScreening(screen);
			reservation.setCustomer(customer);
			reservation.setReservationAmount(calculateReservationTotal(reservation.getSeats()));
			addSeatsToReservation(reservation);
			resRepo.save(reservation); 
			return convertToReservationResponse(reservation);
		} catch (DataIntegrityViolationException e) {
			Logger.error("Exception occured while trying to create a screening");  
			throw new AuthenticationException("Seats not available"); 
		}
	}
	
	//Delete the reservation 
	public void deleteReservation (Long reservationId) throws Exception {
			try {
				resRepo.deleteById(reservationId);			
			} catch (Exception e) {
				Logger.error("Exception occured while trying to delete reservation: " + reservationId, e); 
				throw new Exception("Unable to delete reservation."); 
			}
		}
	
	private ReservationResponse convertToReservationResponse(Reservation reservation) {
		ReservationResponse response = new ReservationResponse();
		Set<Seat> seats = reservation.getSeats();
		response.setReservationId(reservation.getReservationId());
		response.setCustomerEmail(reservation.getCustomer().getEmail());
		response.setPrice(calculateReservationTotal(reservation.getSeats()));
		response.setScreeningId(reservation.getScreening().getScreeningId());
		response.setSeats(convertToSeatResponse(seats));
		return response;
	}
	
	private Set<SeatResponse> convertToSeatResponse(Set<Seat> seats) {
		Set<SeatResponse> seatList = new HashSet<SeatResponse>();
		for(Seat seat : seats) {
			SeatResponse response = new SeatResponse();
			response.setRowName(seat.getRowName());
			response.setSeatNumber(seat.getSeatNumber());
			response.setSeatPrice(seat.getSeatPrice());
			seatList.add(response);
		}
		return seatList;
	}
	
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