package com.jsp.CloneAPIBookMyShow.DTO;

import java.time.LocalDateTime;

import com.jsp.CloneAPIBookMyShow.enums.BookingStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class BookingDto {
	
	private long bookingId;
	private LocalDateTime bookingFromTime;
	private LocalDateTime bookingTillTime;
	private long seatId;
//booking status
	private BookingStatus bookingStatus; 
	private double seatprice;
}
