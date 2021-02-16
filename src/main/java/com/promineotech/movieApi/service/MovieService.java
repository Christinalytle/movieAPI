package com.promineotech.movieApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Movie;
import com.promineotech.movieApi.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired 
	private MovieRepository repo; 
	
	public Movie getMovieById (Long id) throws Exception {
		try {
			return repo.findById(id).orElseThrow(); 
		} catch (Exception e) {
			//logger 
			throw e; 
		}
	}
	
	public Movie createMovie (Movie movie) {
		return repo.save(movie); 	
	}
	
	public Iterable<Movie> getMovies() {
		return repo.findAll();  
	}
	
	public Movie updateMovie (Movie movie, Long id) throws Exception {
		try {
			Movie oldMovie = repo.findById(id).orElseThrow(); 
			oldMovie.setDescription(movie.getDescription());
			oldMovie.setName(movie.getName()); 
			return repo.save(oldMovie); 
		} catch (Exception e) {
			///Logger
			throw new Exception ("Unable to update product."); 
		}
	}
	
	public void deleteMovie(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			//logger
			throw new Exception("Unable to delete product."); 
		}
	}

}
