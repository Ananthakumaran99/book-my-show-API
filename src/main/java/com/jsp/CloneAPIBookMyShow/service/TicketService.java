package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.Controller.BookingDao;
import com.jsp.CloneAPIBookMyShow.DAO.CustomerDao;
import com.jsp.CloneAPIBookMyShow.DAO.SeatDao;
import com.jsp.CloneAPIBookMyShow.DAO.ShowDao;
import com.jsp.CloneAPIBookMyShow.DAO.TicketDao;
import com.jsp.CloneAPIBookMyShow.entity.Booking;
import com.jsp.CloneAPIBookMyShow.entity.Customer;
import com.jsp.CloneAPIBookMyShow.entity.MovieShow;
import com.jsp.CloneAPIBookMyShow.entity.Seat;
import com.jsp.CloneAPIBookMyShow.entity.Ticket;
import com.jsp.CloneAPIBookMyShow.enums.BookingStatus;
import com.jsp.CloneAPIBookMyShow.enums.SeatType;
import com.jsp.CloneAPIBookMyShow.enums.ShowStatus;
import com.jsp.CloneAPIBookMyShow.enums.TicketStatus;
import com.jsp.CloneAPIBookMyShow.exception.CustomerIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.SeatIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ShowIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ShowIsNotActiveException;
import com.jsp.CloneAPIBookMyShow.exception.TicketAlreadyCancelledException;
import com.jsp.CloneAPIBookMyShow.exception.TicketAlreadyExpiredException;
import com.jsp.CloneAPIBookMyShow.exception.TicketCannotBeCancelledException;
import com.jsp.CloneAPIBookMyShow.exception.TicketIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class TicketService {

	@Autowired
	private TicketDao dao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ShowDao showDao;

	@Autowired
	private SeatDao seatDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private TicketDao ticketDao;

	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(long customerId, long showId, long seatId) {
		Customer dbCustomer = customerDao.getCustomerById(customerId);
		Ticket ticket = new Ticket();

		if (dbCustomer != null) {
			ticket.setCustomer(dbCustomer);
		} else {
			throw new CustomerIdNotFoundException("sorry failed to add ticket");
		}
		MovieShow dbMovieShow = showDao.getShowById(showId);
		if (dbMovieShow != null) {

			if (dbMovieShow.getShowStatus().equals(ShowStatus.ACTIVE)) {
				ticket.setShow(dbMovieShow);
			} else {
				throw new ShowIsNotActiveException("sorry failed to book ticket");
			}

		} else {
			throw new ShowIdNotFoundException("sorry failed to book ticket");
		}

		List<Booking> bookings = new ArrayList<Booking>();
		List<Seat> seats = new ArrayList<Seat>();
		double totalprice = 0;
		Seat dbSeat = seatDao.getSeatById(seatId);
		if (dbSeat != null) {
			Booking booking = new Booking();
			booking.setSeatId(dbSeat.getSeatId());
			booking.setSeatType(dbSeat.getSeatType());
			booking.setBookingStatus(BookingStatus.ACTIVE);
			booking.setBookingFromTime(dbMovieShow.getShowStartTime());
			booking.setBookingTillTime(dbMovieShow.getShowEndTime());

			SeatType seatType = booking.getSeatType();
			switch (seatType) {
			case CLASSIC:
				booking.setSeatprice(dbMovieShow.getClassicSeatPrice());
				totalprice += dbMovieShow.getClassicSeatPrice();
				break;

			case GOLD:
				booking.setSeatprice(dbMovieShow.getGoldSeatPrice());
				totalprice += dbMovieShow.getGoldSeatPrice();
				break;

			case PREMIUM:
				booking.setSeatprice(dbMovieShow.getPremiumSeatPrice());
				totalprice += dbMovieShow.getPremiumSeatPrice();
				break;

			}
			bookings.add(booking);
			seats.add(dbSeat);

			bookingDao.saveBooking(booking);
			ticket.setBookings(bookings);
			ticket.setTicketPrice(totalprice);
			ticket.setTicketStatus(TicketStatus.ACTIVE);
			Ticket dbTicket = ticketDao.saveTicket(ticket);

			ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();
			structure.setMessage("ticket booked successfully");
			structure.setData(dbTicket);
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);

		} else {
			throw new SeatIdNotFoundException("sorry failed to book ticket");
		}
	}

	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(long ticketId) {
		Ticket dbTicket = ticketDao.getTicketById(ticketId);
		if (dbTicket != null) {
			if (dbTicket.getShow().getShowStatus().equals(ShowStatus.ON_GOING)) {
				throw new TicketCannotBeCancelledException("Sorry failed to cancel ticket ");
			} else if (dbTicket.getTicketStatus().equals(TicketStatus.CANCELLED)) {
				throw new TicketAlreadyCancelledException("sorry failed to cancel ticket");
			} else if (dbTicket.getTicketStatus().equals(TicketStatus.EXPIRED)) {
				throw new TicketAlreadyExpiredException("sorry failed to cancel ticket ");
			} else {
				List<Booking> bookings = dbTicket.getBookings();
				for (Booking booking : bookings) {
					booking.setBookingStatus(BookingStatus.CANCELLED);
					bookingDao.saveBooking(booking);
				}
				dbTicket.setTicketStatus(TicketStatus.CANCELLED);
				ticketDao.saveTicket(dbTicket);
				ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();
				structure.setMessage("ticket cancelled successfully");
				structure.setData(dbTicket);
				structure.setStatus(HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.NOT_FOUND);
			}
		} else {
			throw new TicketIdNotFoundException("sorry failed to cancel ticket");

		}
	}

}
