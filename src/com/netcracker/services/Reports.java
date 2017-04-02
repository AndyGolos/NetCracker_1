package com.netcracker.services;

import java.util.Iterator;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.users.Client;
import com.netcracker.beans.users.User;

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
			throw new IllegalArgumentException();
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

	public static void startMenu() {
		System.out.println("-----------------------");
		System.out.println("1. Авторизация");
		System.out.println("0. Выход");
		System.out.println("-----------------------");
	}

	public static void clientMenu() {
		System.out.println("-----------------------");
		System.out.println("1. Пополнить счёт");
		System.out.println("2. Совершить платёж");
		System.out.println("3. Заблокировать карточку");
		System.out.println("4. Посмотреть имеющиеся карточки");
		System.out.println("5. Сохранить данные в файл");
		System.out.println("6. Назад");
		System.out.println("0. Выход");
		System.out.println("-----------------------");
	}

	public static void adminMenu() {
		System.out.println("-----------------------");
		System.out.println("1. Просмотреть всех юзеров в системе");
		System.out.println("2. Вывести всех юзеров отсортированных по id");
		System.out.println("3. Разблокировать карточку");
		System.out.println("4. Назад");
		System.out.println("0. Выход");
		System.out.println("-----------------------");
	}

	public static void clientServices() {
		System.out.println("-----------------------");
		System.out.println("Выбирете услугу, которую хотите оплатить:");
		System.out.println("1. Машину");
		System.out.println("2. Квартиру");
		System.out.println("3. Стоянку");
		System.out.println("0. Назад");
		System.out.println("-----------------------");
	}

}
