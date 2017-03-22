package com.netcracker.exceptions;

public class NoUserFoundException extends RuntimeException {

	private static final long serialVersionUID = -2865924508105486986L;

	public NoUserFoundException() {
		super();
	}

	public NoUserFoundException(String message) {
		super(message);
	}

}
