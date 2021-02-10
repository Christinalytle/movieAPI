package com.promineotech.movieApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.movieApi.entity.Reservation;

public interface ReservationRepository extends JpaRepository <Reservation, Long> {

}
