package com.netcracker.services;

import java.util.ArrayList;
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

	public void sortUsersById() {
		List<User> sortedList = new ArrayList<>(PaymentSystemManager.users);
		SortById id;
		for (int i = sortedList.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				id = new SortById(sortedList.get(j).getId());
				User user, secondUser;
				if (id.compareTo(sortedList.get(j + 1)) == 1) {
					user = sortedList.get(j + 1);
					secondUser = sortedList.get(j);
					sortedList.set(j + 1, secondUser);
					sortedList.set(j, user);
				}
			}
		}

		int counter = 1;
		for (User user : sortedList) {
			System.out.println(counter + ". " + user);
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
