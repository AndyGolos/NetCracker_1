package com.netcracker.services;

import java.util.Comparator;

import com.netcracker.beans.users.User;

public class UserComparator implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {
		if (user1.getName().equals(user2.getName()) && user1.getSurname().equals(user2.getSurname())
				&& user1.getPassword() == user2.getPassword()) {
			return 1;
		} else
			return 0;
	}
}
