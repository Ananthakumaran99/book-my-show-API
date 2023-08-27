package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class ShowIdNotFoundException extends RuntimeException{

	private String message;

	public ShowIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
