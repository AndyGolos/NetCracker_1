package com.netcracker.services;

import java.util.Comparator;

import com.netcracker.beans.users.User;

public class UserComparator implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {
		if (user1.getName().toLowerCase().equals(user2.getName().toLowerCase())
				&& user1.getSurname().toLowerCase().equals(user2.getSurname().toLowerCase())
				&& user1.getPassword() == user2.getPassword()) {
			return 1;
		} else
			return 0;
	}
}
