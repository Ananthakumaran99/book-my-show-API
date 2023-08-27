package com.jsp.CloneAPIBookMyShow.DTO;

import java.time.LocalDateTime;

import com.jsp.CloneAPIBookMyShow.enums.Genre;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class MovieDto {
	
	private long movieId;
	private String movieName;
	// genre
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	private LocalDateTime movieDuration;
	private String movieDescription;
	private String Language;

}
