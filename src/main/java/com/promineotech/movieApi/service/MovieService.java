package com.promineotech.movieApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.movieApi.entity.Movie;
import com.promineotech.movieApi.repository.MovieRepository;

@Service
public class MovieService {
	
	private static final Logger Logger = LogManager.getLogger(MovieService.class); 
	
	@Autowired 
	private MovieRepository repo; 
	
	//GET (find) a movie by its id
	public Movie getMovieById (Long id) throws Exception {
		try {
			return repo.findById(id).orElseThrow(); 
		} catch (Exception e) {
			Logger.error("Exception occured while trying to find movie "+ id, e);
			throw e; 
		}
	}
	
	//POST (create) a movie
	public Movie createMovie (Movie movie) {
		return repo.save(movie); 	
	}
	
	//GET all movies 
	public Iterable<Movie> getMovies() {
		return repo.findAll();  
	}
	
	//PUT (update) a movie by its id 
	public Movie updateMovie (Movie movie, Long id) throws Exception {
		try {
			Movie oldMovie = repo.findById(id).orElseThrow(); 
			oldMovie.setDescription(movie.getDescription());
			oldMovie.setName(movie.getName()); 
			return repo.save(oldMovie); 
		} catch (Exception e) {
			Logger.error("Exception occured while trying to update movie " + id, e);
			throw new Exception ("Unable to update product."); 
		}
	}
	
	//DELETE a movie by its id
	public void deleteMovie(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			Logger.error("Exception occured while trying to delete movie "+id, e);
			throw new Exception("Unable to delete product."); 
		}
	}

}
