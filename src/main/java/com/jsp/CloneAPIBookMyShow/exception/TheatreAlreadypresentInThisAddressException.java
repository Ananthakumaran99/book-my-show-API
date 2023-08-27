package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class TheatreAlreadypresentInThisAddressException extends RuntimeException {

	private String message;

	public TheatreAlreadypresentInThisAddressException(String message) {
		super();
		this.message = message;
	}
}
