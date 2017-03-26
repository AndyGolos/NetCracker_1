package com.netcracker.exceptions;

public class NotEnoughMoneyException extends RuntimeException {

	private static final long serialVersionUID = -934537907172651043L;

	public NotEnoughMoneyException() {
		super();
	}

	public NotEnoughMoneyException(String message) {
		super(message);
	}

}
