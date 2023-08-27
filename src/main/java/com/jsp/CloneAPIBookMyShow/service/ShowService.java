package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.DAO.MovieDao;
import com.jsp.CloneAPIBookMyShow.DAO.ScreenDao;
import com.jsp.CloneAPIBookMyShow.DAO.ShowDao;
import com.jsp.CloneAPIBookMyShow.DAO.TheatreDao;
import com.jsp.CloneAPIBookMyShow.DTO.MovieShowDto;
import com.jsp.CloneAPIBookMyShow.entity.Movie;
import com.jsp.CloneAPIBookMyShow.entity.MovieShow;
import com.jsp.CloneAPIBookMyShow.entity.Screen;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneAPIBookMyShow.exception.MovieIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ScreenAlreadyAllotedException;
import com.jsp.CloneAPIBookMyShow.exception.ScreenIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ShowIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class ShowService {

	@Autowired
	private ShowDao dao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private ScreenDao screenDao;

	@Autowired
	private MovieDao movieDao;

	public ResponseEntity<ResponseStructure<MovieShow>> addShow(long theatreId, MovieShowDto showDto) {

		Theatre dbTheatre = theatreDao.getTheatreById(theatreId);
		if (dbTheatre != null) {

			MovieShow movieshow = this.modelMapper.map(showDto, MovieShow.class);
			long screenId = movieshow.getScreenId();
			Screen dbScreen = screenDao.getSceenById(screenId);

			if (dbScreen != null) {
				if (dbScreen.getScreenAvailability().equals(ScreenAvailability.NOT_ALLOTTED)) {
					long movieId = movieshow.getMovieId();
					Movie dbMovie = movieDao.getMovieById(movieId);

					if (dbMovie != null) {
						movieshow.setMovieDescription(dbMovie.getMovieDescription());
						movieshow.setMovieDuration(dbMovie.getMovieDuration());
						movieshow.setMovieLanguage(dbMovie.getLanguage());
						movieshow.setMovieName(dbMovie.getMovieName());
						movieshow.setScreenName(dbScreen.getScreenName());
						movieshow.setTheatre(dbTheatre);
						MovieShow dbShow = dao.addShow(movieshow);

						if (dbTheatre.getShow().isEmpty()) {
							List<MovieShow> list = new ArrayList<MovieShow>();
							list.add(movieshow);
							dbTheatre.setShow(list);
							theatreDao.updateTheatre(theatreId, dbTheatre);
						} else {
							List<MovieShow> list = dbTheatre.getShow();
							list.add(movieshow);
							dbTheatre.setShow(list);
							theatreDao.updateTheatre(theatreId, dbTheatre);
						}
						ResponseStructure<MovieShow> structure = new ResponseStructure<MovieShow>();
						structure.setMessage("show added successfully");
						structure.setStatus(HttpStatus.CREATED.value());

						structure.setData(this.modelMapper.map(dbShow, MovieShowDto.class));
						return new ResponseEntity<ResponseStructure<MovieShow>>(structure, HttpStatus.CREATED);

					} else {
						throw new MovieIdNotFoundException("sorry failed to add show");
					}

				} else {
					throw new ScreenAlreadyAllotedException("screen is already alloted");
				}

			} else {
				throw new ScreenIdNotFoundException("sorry failed to add show");
			}

		} else {
			throw new TheatreIdNotFoundException("theatre is not present to add show");
		}
	}

	public ResponseEntity<ResponseStructure<MovieShow>> updateShow(long showId, MovieShowDto showDto) {
		MovieShow movieShow = this.modelMapper.map(showDto, MovieShow.class);
		MovieShow dbMovieShow = dao.updateShow(showId, movieShow);

		if (dbMovieShow != null) {
			ResponseStructure<MovieShow> structure = new ResponseStructure<MovieShow>();
			structure.setMessage("show added successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbMovieShow);
			return new ResponseEntity<ResponseStructure<MovieShow>>(structure, HttpStatus.CREATED);
		} else {
			throw new ShowIdNotFoundException("sorry failed to update show");
		}

	}

	public ResponseEntity<ResponseStructure<MovieShow>> getShowById(long showId) {
		MovieShow dbMovieShow = dao.getShowById(showId);

		if (dbMovieShow != null) {
			ResponseStructure<MovieShow> structure = new ResponseStructure<MovieShow>();
			structure.setMessage("show found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbMovieShow);
			return new ResponseEntity<ResponseStructure<MovieShow>>(structure, HttpStatus.FOUND);
		} else {
			throw new ShowIdNotFoundException("sorry failed to fetch show");
		}

	}
	
	public ResponseEntity<ResponseStructure<MovieShow>> deleteShowById(long showId) {
		MovieShow dbMovieShow = dao.deleteShowById(showId);

		if (dbMovieShow != null) {
			ResponseStructure<MovieShow> structure = new ResponseStructure<MovieShow>();
			structure.setMessage("show deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbMovieShow);
			return new ResponseEntity<ResponseStructure<MovieShow>>(structure, HttpStatus.OK);
		} else {
			throw new ShowIdNotFoundException("sorry failed to delete show");
		}

	}
}
