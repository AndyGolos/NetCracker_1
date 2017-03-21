package com.netcracker.enums;

public enum CardStatus {
	ACTIVE, BLOCKED;

	public String getStatus() {
		switch (this) {
		case ACTIVE:
			return "активирована";
		case BLOCKED:
			return "заблокирована";
		default:
			return "невозможно определить статус";
		}
	}

}
