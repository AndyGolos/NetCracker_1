package com.netcracker.beans.cards;

import com.netcracker.enums.CardStatus;
import com.netcracker.enums.CardType;

public class CreditCard extends AbstractCard {

	private static final long serialVersionUID = -3513566660997979875L;

	private int moneyOnTheAccount; // количество денег на карточке
	private int valid; // срок действия карточки(лет)

	public CreditCard() {
		super();
	}

	public CreditCard(int id, int pin, CardStatus status, CardType type, int valid) {
		super(id, pin, status, type);
		this.valid = valid;
		this.moneyOnTheAccount = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + moneyOnTheAccount;
		result = prime * result + valid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (moneyOnTheAccount != other.moneyOnTheAccount)
			return false;
		if (valid != other.valid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\t денег на счёте=" + moneyOnTheAccount + ",\t валидна=" + valid + " years";
	}

	public int getMoneyOnTheAccount() {
		return moneyOnTheAccount;
	}

	public void setMoneyOnTheAccount(int moneyOnTheAccount) {
		this.moneyOnTheAccount = moneyOnTheAccount;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

}
