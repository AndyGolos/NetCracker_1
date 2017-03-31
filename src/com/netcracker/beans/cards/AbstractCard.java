package com.netcracker.beans.cards;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.netcracker.enums.CardStatus;
import com.netcracker.enums.CardType;

public abstract class AbstractCard implements Serializable {

	private static final long serialVersionUID = -258804634942215057L;

	private int id;
	private int pinCode;
	private CardStatus status;
	private CardType type;
	private Date timeOfRegistration;
	private static int numberOfCards = 0;

	public AbstractCard(int id, int pinCode, CardStatus status, CardType type) {
		this.id = id;
		this.pinCode = pinCode;
		this.status = status;
		this.type = type;
		this.timeOfRegistration = new Date();
		numberOfCards++;
	}

	// Выводит данные немного кривовато
	@Override
	public String toString() {
		return "id=" + id + ",\t статус=" + status + ", тип=" + type + ",\t\t дата регистрации="
				+ new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(timeOfRegistration);
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPin() {
		return pinCode;
	}

	public void setPin(int pinCode) {
		this.pinCode = pinCode;
	}

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public static int getNumberOfCards() {
		return numberOfCards;
	}

	public Date getTimeOfRegistration() {
		return timeOfRegistration;
	}

	public void setTimeOfRegistration(Date timeOfRegistration) {
		this.timeOfRegistration = timeOfRegistration;
	}
}
