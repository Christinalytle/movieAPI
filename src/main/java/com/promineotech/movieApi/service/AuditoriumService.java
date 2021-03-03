package com.promineotech.movieApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Auditorium;
import com.promineotech.movieApi.repository.AuditoriumRepository;

@Service
public class AuditoriumService {
	
	private static final Logger Logger = LogManager.getLogger(AuditoriumService.class); 
	
	@Autowired
	private AuditoriumRepository repo; 
	
	//GET all auditoriums 
	public Iterable<Auditorium> getAuditoriums(){
		return repo.findAll(); 
	}
	
	//POST (create) an auditorium
	public Auditorium createAuditorium (Auditorium auditorium) {
		return repo.save(auditorium); 
	}
	
	//PUT (update) an auditorium
	public Auditorium updateAuditorium (Auditorium auditorium, Long id) throws Exception {
		try {
			Auditorium oldAuditorium = repo.findById(id).orElseThrow();
			oldAuditorium.setAuditoriumNumber(auditorium.getAuditoriumNumber());
			return repo.save(oldAuditorium); 
		} catch (Exception e) {
			Logger.error("Exception occured while trying to update auditorium "+ id, e); 
			throw new Exception ("Unable to update auditorium"); 
		}
	}
	
	//DELETE an auditorium
	public void deleteAuditorium(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			Logger.error("Exception occured while trying to delete movie "+ id, e); 
			throw new Exception ("Unable to delete auditorium");
		}
	}
	
	
}
