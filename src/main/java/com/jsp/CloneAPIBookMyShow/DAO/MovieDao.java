package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.MovieRepo;
import com.jsp.CloneAPIBookMyShow.entity.Movie;

@Repository
public class MovieDao {

	@Autowired
	private MovieRepo repo;

	public Movie saveMovie(Movie movie) {
		return repo.save(movie);
	}

	public Movie UpdateMovie(long movieId, Movie movie) {
		Optional<Movie> optional = repo.findById(movieId);
		if (optional.isPresent()) {
			movie.setMovieId(movieId);
			movie.setProductionHouse(optional.get().getProductionHouse());

			return repo.save(movie);

		}
		return null;
	}

	public Movie getMovieById(long movieId) {
		Optional<Movie> optional = repo.findById(movieId);

		if (optional.isPresent()){
			return optional.get();
		}
		return null;
		
	}
	
	public Movie deleteMovieById(long movieId) {
		Optional<Movie> optional = repo.findById(movieId);

		if (optional.isPresent()){
			
			Movie movie=optional.get();
			movie.setProductionHouse(null);
			repo.delete(movie);
			
			return movie;
		}
		return null;
		
	}
}
