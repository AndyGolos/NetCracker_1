package com.netcracker.exceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2865924508105486986L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}

}
