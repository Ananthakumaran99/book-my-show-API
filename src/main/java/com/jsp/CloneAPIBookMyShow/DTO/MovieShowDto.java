package com.jsp.CloneAPIBookMyShow.DTO;

import java.time.LocalDateTime;

import com.jsp.CloneAPIBookMyShow.enums.ShowStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieShowDto {
	private long showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	
	// show status
	private ShowStatus showStatus;
	
	private String showLocation;

	private long movieId;
	private String movieName;
	// genre
	private String genre;
	
	private LocalDateTime movieDuration;
	private String movieDescription;
	private String movieLanguage;

	private long screenId;
	private String screenName;
	private double classicSeatPrice;
	private double goldSeatPrice;
	private double premiumSeatPrice;
}
