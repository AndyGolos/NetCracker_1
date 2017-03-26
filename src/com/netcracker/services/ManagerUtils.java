package com.netcracker.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;
import com.netcracker.beans.users.Client;
import com.netcracker.beans.users.User;
import com.netcracker.enums.CardStatus;
import com.netcracker.exceptions.NoUserFoundException;
import com.netcracker.exceptions.NotEnoughMoneyException;

public class ManagerUtils {

	private static Scanner scanner;

	private ManagerUtils() {
	}

	public static int getInputNumber() {
		int choise = -1;
		while (choise < 0) {
			try {
				scanner = new Scanner(System.in);
				choise = scanner.nextInt();
				if (choise >= 0) {
					break;
				} else {
					System.out.println("Введено некорректное число! Повторите ввод:");
				}
			} catch (InputMismatchException e) {
				System.out.println("Введите корректные данные!");
			}
		}
		return choise;
	}

	public static String getInputString() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static User validate(User currentUser) {
		if (currentUser == null) {
			throw new IllegalArgumentException();
		}
		Iterator<User> iterator = PaymentSystemManager.users.iterator();
		UserComparator userComparator = new UserComparator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (userComparator.compare(currentUser, user) == 1) {
				return user;
			}
		}
		throw new NoUserFoundException();
	}

	public static AbstractCard getCard(List<AbstractCard> listOfUserCards, int index) {
		if (listOfUserCards == null) {
			throw new IllegalArgumentException();
		}
		try {
			return listOfUserCards.get(index - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Введено неверное число!");
			return null;
		}

	}

	public static boolean cardIsBlocked(AbstractCard card) {
		if (card == null) {
			throw new IllegalArgumentException();
		}
		if (card.getStatus().equals(CardStatus.BLOCKED)) {
			return true;
		} else
			return false;
	}

	public static AbstractCard getCard(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		int choise;
		System.out.println("-----------------------");
		System.out.println("Введите номер карточки:");
		System.out.println("-----------------------");
		while (true) {
			choise = ManagerUtils.getInputNumber();
			if (choise > user.getUserCards().size() || choise == 0) {
				System.out.println("Введено некорректное число! Повторите ввод:");
				continue;
			} else
				break;
		}
		return getCard(user.getUserCards(), choise);
	}

	public static boolean saveInDocument(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		try (FileWriter writer = new FileWriter(
				new File("src/com/netcracker/files/outputfiles/" + user.hashCode() + ".txt"))) {

			user.getUserCards().forEach(card -> {
				try {
					writer.write(card + "\n");
				} catch (IOException e) {
					System.out.println("IOException in inner saveInDocument!");
					System.exit(0);
				}
			});

			return true;

		} catch (IOException e) {
			System.out.println("IOException in outer saveInDocument!");
			System.exit(0);
			return false;
		}
	}

	public static User getUser(List<User> users, int index) {
		if (users == null) {
			throw new IllegalArgumentException();
		}

		try {
			return users.get(index - 1);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public static boolean payFor(Client client, CreditCard currentCard) {

		if (client == null || currentCard == null) {
			throw new IllegalArgumentException();
		}

		int money = ManagerUtils.getInputNumber();
		try {
			if (client.replenishAccount(currentCard, money)) {
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

	public static User getClientFromListOfUsers(List<User> users) {

		if (users == null) {
			throw new IllegalArgumentException();
		}

		int choise;
		User currentuser;
		System.out.println("-----------------------");
		System.out.println("Выбирите клиента:");
		System.out.println("-----------------------");
		while (true) {

			choise = ManagerUtils.getInputNumber();

			if (choise <= 0 || !(ManagerUtils.getUser(users, choise) instanceof Client)) {
				System.out.println("Введено некорректное число! Повторите ввод:");
				continue;
			} else
				break;
		}

		currentuser = ManagerUtils.getUser(users, choise);
		return currentuser;
	}

}
