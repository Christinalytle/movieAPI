package com.promineotech.movieApi.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {

	private Long movieId; //primary key
	private String name; 
	private String description;
	
	//Screenings can have many movies 
	@JsonIgnore 
	private Set<Screening> screenings; 
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	//Foreign key screeningId 
	@OneToMany(mappedBy = "movie")
	public Set<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(Set<Screening> screenings) {
		this.screenings = screenings;
	}

	
	
	
}
