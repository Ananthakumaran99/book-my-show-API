package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.TicketRepo;
import com.jsp.CloneAPIBookMyShow.entity.Ticket;

@Repository
public class TicketDao {

	@Autowired
	private TicketRepo ticketRepo;
	
	public Ticket saveTicket(Ticket ticket ) {
		return ticketRepo.save(ticket);
	} 
	
	public Ticket getTicketById(long ticketId) {
		 Optional<Ticket> optional = ticketRepo.findById(ticketId);
		 return optional.get();
	}
}
