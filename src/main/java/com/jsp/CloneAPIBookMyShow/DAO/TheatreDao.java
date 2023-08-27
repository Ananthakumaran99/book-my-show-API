package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.TheatreRepo;
import com.jsp.CloneAPIBookMyShow.entity.MovieShow;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;

@Repository
public class TheatreDao {
	@Autowired
	private TheatreRepo repo;
	
	public Theatre saveTheatre(Theatre theatre) {
		 return repo.save(theatre);	}
	
	public Theatre updateTheatre(long theatreId, Theatre theatre) {
		 Optional<Theatre> optional = repo.findById(theatreId);
		if(optional.isPresent()) {
			Theatre oldTheatre=optional.get();
			theatre.setTheatreId(theatreId);
			theatre.setAddress(oldTheatre.getAddress());
			theatre.setShow(oldTheatre.getShow());
			theatre.setOwner(oldTheatre.getOwner());
			theatre.setScreen(oldTheatre.getScreen());
			repo.save(theatre);
			return theatre;
		}return null;
		}

	public Theatre getTheatreById(long theatreId) {
		 Optional<Theatre> optional = repo.findById(theatreId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
		}
	
	public Theatre deleteTheatreById(long theatreId) {
		 Optional<Theatre> optional = repo.findById(theatreId);
		if(optional.isPresent()) {
			Theatre theatre = optional.get();
			theatre.setOwner(null);
			theatre.setAddress(null);
			List<MovieShow>list=optional.get().getShow();
			for (MovieShow movieShow : list) {
				movieShow.setTheatre(null);
			}
		 repo.delete(theatre);
		 return optional.get();
		}
		return null;
		}
	
	
}

