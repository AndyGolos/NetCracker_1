package com.netcracker.beans.users;

import java.util.Date;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;
import com.netcracker.enums.CardStatus;
import com.netcracker.exceptions.NotEnoughMoneyException;
import com.netcracker.interfaces.ClientActions;

public class Client extends User implements ClientActions {

	private static final long serialVersionUID = -5675775384827995608L;

	public Client(int id, String name, String surname, int password, Date dateOfBirth) {
		super(id, name, surname, password, dateOfBirth);
	}

	@Override
	public boolean blockCard(AbstractCard card) {
		if (card.getStatus().equals(CardStatus.ACTIVE)) {
			card.setStatus(CardStatus.BLOCKED);
			return true;
		} else
			return false;
	}

	@Override
	public boolean topUpAccount(CreditCard card, int money) {
		if (card == null) {
			throw new IllegalArgumentException();
		}
		if (money <= 0) {
			return false;
		} else {
			card.setMoneyOnTheAccount(card.getMoneyOnTheAccount() + money);
			return true;
		}

	}

	@Override
	public boolean replenishAccount(CreditCard card, int money) {
		if (money <= 0) {
			return false;
		} else if ((card.getMoneyOnTheAccount() - money) <= 0) {
			throw new NotEnoughMoneyException();
		} else {
			card.setMoneyOnTheAccount(card.getMoneyOnTheAccount() - money);
			return true;
		}
	}

}
