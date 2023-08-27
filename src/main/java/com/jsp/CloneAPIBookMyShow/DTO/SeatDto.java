package com.jsp.CloneAPIBookMyShow.DTO;

import com.jsp.CloneAPIBookMyShow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDto {
	private long seatId;
	// seat type
	private SeatType seatType;
}
