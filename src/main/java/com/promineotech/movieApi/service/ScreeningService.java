package com.promineotech.movieApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Auditorium;
import com.promineotech.movieApi.entity.Movie;
import com.promineotech.movieApi.entity.Screening;
import com.promineotech.movieApi.repository.AuditoriumRepository;
import com.promineotech.movieApi.repository.MovieRepository;
import com.promineotech.movieApi.repository.ScreeningRepository;

@Service 
public class ScreeningService {
	
	private static final Logger Logger = LogManager.getLogger(ScreeningService.class); 
	
	@Autowired 
	private ScreeningRepository repo; 
	
	@Autowired 
	private AuditoriumRepository audRepo; 
	
	@Autowired
	private MovieRepository movieRepo; 
	
	//GET a screening by its Id
	public Screening getScreeningById (Long id) throws Exception {
		try {
			return repo.findById(id).orElseThrow(); 
		} catch (Exception e) {
			Logger.error("Exception occured while trying to get Screenings", e);
			throw e; 
		}
	}
	
	//GET all screenings 
	public Iterable<Screening> getScreenings() {
		return repo.findAll(); 
	}
	
	//POST (create) a screening by finding the auditorium and movie ID, and saving 
	//it to the reservation while adding a time. 
	public Screening createScreening (Long auditoriumId, Long movieId, String time) {
		Screening screening = new Screening(); 
		Auditorium aud = audRepo.findById(auditoriumId).orElseThrow(); 
		Movie mov = movieRepo.findById(movieId).orElseThrow(); 
		screening.setAuditorium(aud);
		screening.setMovie(mov);
		screening.setTime(time);
		return repo.save(screening); 
	}
	
	
	//PUT (update) a screening by its id
	public Screening updateScreening (Screening screening, Long id) throws Exception {
		try {
			Screening oldScreening = repo.findById(id).orElseThrow(); 
			oldScreening.setAuditorium(screening.getAuditorium());
			oldScreening.setMovie(screening.getMovie());
			oldScreening.setTime(screening.getTime());
			return repo.save(oldScreening); 
		} catch (Exception e) {
			Logger.error("Exception occured while trying to update Screening " + id, e); 
			throw new Exception ("Unable to update product.");
		}
	}
	
	//DELETE a screening by its id 
	public void deleteScreening (Long id) throws Exception {
			try {
				repo.deleteById(id);
			} catch (Exception e) {
				Logger.error("Exception occured while trying to delete Screening "+ id, e);
				throw new Exception ("Unable to delete screening.");
			}
		}
	
	

}


