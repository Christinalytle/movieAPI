package com.promineotech.movieApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.movieApi.entity.Auditorium;


public interface AuditoriumRepository extends JpaRepository <Auditorium, Long> {

}
