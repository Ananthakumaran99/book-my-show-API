package com.jsp.CloneAPIBookMyShow.DTO;

import com.jsp.CloneAPIBookMyShow.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
	private long ticketId;
	private double ticketPrice;
//ticket status
	private TicketStatus ticketStatus;
}
