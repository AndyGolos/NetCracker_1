package com.netcracker.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.users.User;
import com.netcracker.enums.CardStatus;
import com.netcracker.exceptions.NoUserFoundException;

public class ManagerUtils {

	private static Scanner scanner;

	private ManagerUtils() {
	}

	// Добавить закрытие отока сканнер
	// Избавиться от всех стектрейсов
	public static int getInputNumber() {
		int choise = -1;
		while (choise < 0) {
			try {
				scanner = new Scanner(System.in);
				choise = scanner.nextInt();
				if (choise >= 0) {
					break;
				} else {
					System.out.println("Введено некорректное число!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Введите число!");
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
		System.out.println("Введите номер карточки:");
		int choise = ManagerUtils.getInputNumber();
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
					e.printStackTrace();
				}
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
			System.out.println("Введите корректное значение!");
			return null;
		}
	}

}
