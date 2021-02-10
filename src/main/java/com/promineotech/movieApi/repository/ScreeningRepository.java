package com.promineotech.movieApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.movieApi.entity.Screening;

public interface ScreeningRepository extends JpaRepository <Screening, Long> {

}
