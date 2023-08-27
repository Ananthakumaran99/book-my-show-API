package com.jsp.CloneAPIBookMyShow.DTO;

import com.jsp.CloneAPIBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneAPIBookMyShow.enums.ScreenStatus;
import com.jsp.CloneAPIBookMyShow.enums.ScreenType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenDto {
	private long screenId;
	private String screenName;
//screen type
	private ScreenType screenType;

//screen availability
	private ScreenAvailability screenAvailability;

//screen status
	private ScreenStatus screenStatus;
	
	private int noOfClassicSeats;
	private int noOfPremiumSeats;
	private int noOfGoldSeats;
}
