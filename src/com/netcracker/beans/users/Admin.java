package com.netcracker.beans.users;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.enums.CardStatus;
import com.netcracker.interfaces.AdminActions;

public class Admin extends User implements AdminActions {

	private static final long serialVersionUID = 388582222333672349L;

	public Admin(int id, String name, String surname, int password, Date dateOfBirth) {
		super(id, name, surname, password, dateOfBirth);
	}

	@Override
	public void getAllUsersInSystem(List<User> users) {
		if (users == null) {
			throw new IllegalArgumentException();
		}
		int counter = 1;
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			System.out.println(counter + ". "+iterator.next());
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
