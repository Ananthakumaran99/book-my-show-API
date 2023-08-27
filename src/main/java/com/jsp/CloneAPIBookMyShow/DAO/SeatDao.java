package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.SeatRepo;
import com.jsp.CloneAPIBookMyShow.entity.Seat;

@Repository
public class SeatDao {

	@Autowired
	private SeatRepo repo;

	public Seat saveSeat(Seat seat) {
		return repo.save(seat);

	}

	public Seat updateSeatById(long seatId, Seat seat) {
		Optional<Seat> optional = repo.findById(seatId);
		if (optional.isPresent()) {

			return repo.save(seat);
		}
		return null;
	}

	public Seat deleteSeatById(long seatId) {
		Optional<Seat> optional = repo.findById(seatId);
		if (optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		}
		return null;
	}

	public Seat getSeatById(long seatId) {
		Optional<Seat> optional = repo.findById(seatId);
		if (optional.isPresent()) {

			return optional.get();
		}
		return null;
	}

}
