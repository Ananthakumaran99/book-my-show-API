package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class MovieIdNotFoundException extends RuntimeException {

	public MovieIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;
	
}
