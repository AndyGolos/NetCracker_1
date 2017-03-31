package com.netcracker.services;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;
import com.netcracker.beans.users.Client;
import com.netcracker.enums.CardStatus;
import com.netcracker.exceptions.NotEnoughMoneyException;
import com.netcracker.interfaces.BaseClientActions;
import com.netcracker.interfaces.ReplenishAction;
import com.netcracker.utils.IOUtils;

public class ClientService extends UserService implements BaseClientActions, ReplenishAction {

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

	public boolean payFor(Client client, CreditCard currentCard) {

		if (client == null || currentCard == null) {
			throw new IllegalArgumentException();
		}

		int money = IOUtils.getInputNumber();
		try {
			if (replenishAccount(currentCard, money)) {
				System.out.println("Оплачено!");
				return true;
			} else {
				System.out.println("Введено некорректное число! Повторите ввод!");
				return false;
			}
		} catch (NotEnoughMoneyException e) {
			System.out.println("Недостаточно средств на счёте!");
			return true;
		}
	}

}
