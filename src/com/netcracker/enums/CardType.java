package com.netcracker.enums;

public enum CardType {
	VISA, MAESTRO, MASTERCARD;

	public String getType() {
		switch (this) {
		case VISA:
			return "VISA";
		case MAESTRO:
			return "MAESTRO";
		case MASTERCARD:
			return "MASTERCARD";
		default:
			return "тип не определён";
		}
	}

}
