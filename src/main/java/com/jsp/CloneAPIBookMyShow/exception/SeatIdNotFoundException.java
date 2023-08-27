package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class SeatIdNotFoundException  extends RuntimeException{

	private String message;

	public SeatIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
