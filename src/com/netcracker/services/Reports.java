package com.netcracker.services;

import java.util.Iterator;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.users.Client;
import com.netcracker.beans.users.User;
import com.netcracker.exceptions.IllegalValueException;

public class Reports {

	public static void getAllClients(List<User> users) {
		if (users == null) {
			throw new IllegalArgumentException();
		}
		int counter = 1;
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user instanceof Client) {
				System.out.println(counter + ". " + user);
				counter++;

			}
		}
	}

	public static void getCards(User user) {
		if (user == null) {
			throw new IllegalValueException();
		}
		int counter = 1;
		Iterator<AbstractCard> iterator = user.getUserCards().iterator();
		while (iterator.hasNext()) {
			System.out.println(counter + ". " + iterator.next().toString());
			counter++;
		}
	}

	public static void exit() {
		System.out.println("-----------------------");
		System.out.println("Ждём вас в следующий раз!");
		System.out.println("-----------------------");
		System.exit(0);
	}

}
