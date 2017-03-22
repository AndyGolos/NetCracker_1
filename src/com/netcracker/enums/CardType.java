package com.netcracker.enums;

public enum CardType {
	VISA, MAESTRO, MASTERCARD;

	public String getType() {
		switch (this) {
		case VISA:
		case MAESTRO:
		case MASTERCARD:
			return this.name();
		default:
			return "тип не определён";
		}
	}

}
