package com.promineotech.movieApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Screening;
import com.promineotech.movieApi.repository.ScreeningRepository;

@Service 
public class ScreeningService {
	
	//logger 
	
	@Autowired 
	private ScreeningRepository repo; 
	
	public Screening getScreeningById (Long id) throws Exception {
		try {
			return repo.findById(id).orElseThrow(); 
		} catch (Exception e) {
			//logger
			throw e; 
		}
	}
	
	public Screening createScreening (Screening screening) {
		return repo.save(screening); 
	}
	
	public Iterable<Screening> getScreenings() {
		return repo.findAll(); 
	}
	
	public Screening updateScreening (Screening screening, Long id) throws Exception {
		try {
			Screening oldScreening = repo.findById(id).orElseThrow(); 
			oldScreening.setAuditorium(screening.getAuditorium());
			oldScreening.setMovie(screening.getMovie());
			oldScreening.setTime(screening.getTime());
			return repo.save(oldScreening); 
		} catch (Exception e) {
			//logger 
			throw new Exception ("Unable to update product.");
		}
	}
		
		public void deleteScreening (Long id) throws Exception {
			try {
				repo.deleteById(id);
			} catch (Exception e) {
				//logger 
				throw new Exception ("Unable to delete screening.");
			}
		}
		
}


