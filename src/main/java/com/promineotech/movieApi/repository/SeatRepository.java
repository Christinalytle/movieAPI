package com.promineotech.movieApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.movieApi.entity.Seat;

public interface SeatRepository extends JpaRepository <Seat, Long> {

}
