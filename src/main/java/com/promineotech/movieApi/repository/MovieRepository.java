package com.promineotech.movieApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.movieApi.entity.Movie;

public interface MovieRepository extends JpaRepository <Movie, Long> {

}
