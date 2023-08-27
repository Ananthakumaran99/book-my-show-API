package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.CloneAPIBookMyShow.DAO.MovieDao;
import com.jsp.CloneAPIBookMyShow.DAO.ProductionHouseDao;
import com.jsp.CloneAPIBookMyShow.DTO.MovieDto;
import com.jsp.CloneAPIBookMyShow.entity.Movie;
import com.jsp.CloneAPIBookMyShow.entity.ProductionHouse;
import com.jsp.CloneAPIBookMyShow.exception.MovieIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ProductionHouseIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class MovieService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MovieDao dao;

	@Autowired
	private ProductionHouseDao houseDao;

	public ResponseEntity<ResponseStructure<MovieDto>> saveMovie(long houseId, MovieDto movieDto) {
		ProductionHouse house = houseDao.getProductionHouseById(houseId);
		if (house != null) {
//			add movie
			Movie movie = this.modelMapper.map(movieDto, Movie.class);
			movie.setProductionHouse(house);
			Movie dbMovie = dao.saveMovie(movie);
//			update Production
			if (house.getMovies().isEmpty()) {
				List<Movie> list = new ArrayList<Movie>();
				list.add(movie);
				house.setMovies(list);
				houseDao.updateProductionHouse(houseId, house);
			} else {
				List<Movie> list = house.getMovies();
				list.add(movie);
				house.setMovies(list);
				houseDao.updateProductionHouse(houseId, house);

			}
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie Saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new ProductionHouseIdNotFoundException("sorry failed to add movie ");
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> updateMovie(long movieId, MovieDto movieDto) {

		Movie movie = this.modelMapper.map(movieDto, Movie.class);
		Movie dbMovie = dao.UpdateMovie(movieId, movie);
		if (dbMovie!=null) {
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie Saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new MovieIdNotFoundException("sorry failed to update movie ");
		}
		
		
		
	}

	public ResponseEntity<ResponseStructure<MovieDto>> getMovieById(long movieId) {
		Movie dbMovie =dao.getMovieById(movieId);
		if (dbMovie!=null) {
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new MovieIdNotFoundException("sorry failed to fetch movie ");
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> deleteMovieById(long movieId) {
		Movie dbMovie =dao.deleteMovieById(movieId);
		if (dbMovie!=null) {
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.OK);
		} else {
			throw new MovieIdNotFoundException("sorry failed to delete movie ");
		}
	}
}
