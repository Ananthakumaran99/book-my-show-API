package com.jsp.CloneAPIBookMyShow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.BookingRepo;
import com.jsp.CloneAPIBookMyShow.entity.Booking;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepo repo;
	
	public void saveBooking(Booking booking ) {
		repo.save(booking);
	}
}
