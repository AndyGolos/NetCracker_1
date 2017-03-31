package com.netcracker.services;

import java.util.Iterator;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.users.User;
import com.netcracker.enums.CardStatus;
import com.netcracker.interfaces.AdminActions;

public class AdminService extends UserService implements AdminActions {

	@Override
	public void getAllUsersInSystem(List<User> users) {
		if (users == null) {
			throw new IllegalArgumentException();
		}
		int counter = 1;
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			System.out.println(counter + ". " + iterator.next());
			counter++;
		}

	}

	@Override
	public boolean unblockCard(AbstractCard card) {
		if (card.getStatus().equals(CardStatus.BLOCKED)) {
			card.setStatus(CardStatus.ACTIVE);
			return true;
		} else
			return false;
	}

}
