package com.netcracker.dao;

import java.util.List;
import java.util.ListIterator;

import com.netcracker.beans.users.Admin;
import com.netcracker.beans.users.Client;
import com.netcracker.beans.users.User;
import com.netcracker.exceptions.NoUserFoundException;
import com.netcracker.services.PaymentSystemManager;
import com.netcracker.services.UserComparator;
import com.netcracker.utils.IOUtils;

public class UserDao {

	public User getUser(List<User> users, int index) {
		if (users == null) {
			throw new IllegalArgumentException();
		}

		try {
			return users.get(index - 1);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public User getClient(List<User> users) {

		if (users == null) {
			throw new IllegalArgumentException();
		}

		int choise;
		User currentuser;
		System.out.println("-----------------------");
		System.out.println("Выбирите клиента:");
		System.out.println("-----------------------");
		while (true) {

			choise = IOUtils.getInputNumber();

			if (choise <= 0 || !(getUser(users, choise) instanceof Client)) {
				System.out.println("Введено некорректное число! Повторите ввод:");
				continue;
			} else
				break;
		}

		currentuser = getUser(users, choise);
		return currentuser;
	}

	public User getUser(String name, String surname, int password) {
		User currentUser = new Admin(name, surname, password);
		ListIterator<User> iterator = PaymentSystemManager.users.listIterator();
		UserComparator userComparator = new UserComparator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (userComparator.compare(currentUser, user) == 0) {
				return user;
			}
		}
		throw new NoUserFoundException();
	}

}
