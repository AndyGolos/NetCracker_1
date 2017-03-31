package com.netcracker.services;

import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.users.User;
import com.netcracker.dao.CardDao;
import com.netcracker.enums.CardStatus;

public class CardService {

	private CardDao cardDao;

	public CardService() {
		cardDao = new CardDao();
	}

	public boolean cardIsBlocked(AbstractCard card) {
		if (card == null) {
			throw new IllegalArgumentException();
		}
		if (card.getStatus().equals(CardStatus.BLOCKED)) {
			return true;
		} else
			return false;
	}

	public AbstractCard getCard(List<AbstractCard> listOfUserCards, int index) {
		return cardDao.getCard(listOfUserCards, index);
	}

	public AbstractCard getCard(User user) {
		return cardDao.getCard(user);
	}

}
