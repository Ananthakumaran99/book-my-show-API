package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class ScreenAlreadyAllotedException extends RuntimeException  {

	private String message;

	public ScreenAlreadyAllotedException(String message) {
		super();
		this.message = message;
	}
	
}
