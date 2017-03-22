package com.netcracker.enums;

public enum CardType {
	VISA, MAESTRO, MASTERCARD;

	public String getType() {
		return this.name();
	}

}
