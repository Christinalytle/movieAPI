package com.promineotech.movieApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Auditorium;
import com.promineotech.movieApi.repository.AuditoriumRepository;

@Service
public class AuditoriumService {
	
	//logger
	
	@Autowired
	private AuditoriumRepository repo; 
	
	public Iterable<Auditorium> getAuditoriums(){
		return repo.findAll(); 
	}
	
	public Auditorium createAuditorium (Auditorium auditorium) {
		return repo.save(auditorium); 
	}
	
	public Auditorium updatAuditorium (Auditorium auditorium, Long id) throws Exception {
		try {
			Auditorium oldAuditorium = repo.findById(id).orElseThrow();
			oldAuditorium.setAuditoriumNumber(auditorium.getAuditoriumNumber());
			return repo.save(oldAuditorium); 
		} catch (Exception e) {
			//logger 
			throw new Exception ("Unable to update auditorium"); 
		}
	}
	
	public void removeProduct(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			//logger 
			throw new Exception ("Unable to delete auditorium");
		}
	}
	
	
}
