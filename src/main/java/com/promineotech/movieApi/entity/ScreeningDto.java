package com.promineotech.movieApi.entity;


public class ScreeningDto {
	
	private Long movieId; 
	private Long auditoriumId;
	private String time;
	  
	
	
	public Long getAuditoriumId() {
		return auditoriumId;
	}
	public void setAuditoriumId(Long auditoriumId) {
		this.auditoriumId = auditoriumId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
