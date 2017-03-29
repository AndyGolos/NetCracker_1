package com.netcracker.services;

import java.util.ArrayList;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;
import com.netcracker.beans.users.Admin;
import com.netcracker.beans.users.Client;
import com.netcracker.beans.users.User;
import com.netcracker.exceptions.NoUserFoundException;

public class PaymentSystemManager {

	public static List<User> users = new ArrayList<>();
	public static final String DATA_FILE_NAME = "src/com/netcracker/files/inputfiles/inputdata.txt";

	private PaymentSystemManager() {
	}

	public static void startMenu() {

		// Инициализация данных из файла
		users = Initialization.dataInitialization(users, DATA_FILE_NAME);

		// Если я явно не возвращаю юзеров - то мой лист пустой, почему?
		// System.out.println(users);

		System.out.println("Добрый день");
		while (true) {
			Reports.startMenu();
			switch (ManagerUtils.getInputNumber()) {
			case 1:
				while (true) {

					System.out.println("-----------------------");
					System.out.println("Введите имя:");
					String name = ManagerUtils.getInputString();
					System.out.println("Введите фамилию:");
					String surname = ManagerUtils.getInputString();
					System.out.println("Введите пароль:");
					int password = ManagerUtils.getInputNumber();
					System.out.println("-----------------------");

					User currentUser = null;
					try {
						currentUser = ManagerUtils.validate(new User(name, surname, password));
					} catch (NoUserFoundException e) {
						System.out.println("Такого пользователя нет в системе!");
						break;
					}

					// Узнаём, кем является наш User. И запсукаем
					// переопределённый метод для клиента и админа
					if (currentUser instanceof Client) {
						workWithUser((Client) currentUser);
					} else {
						workWithUser((Admin) currentUser);
					}
					break;
				}
				break;
			case 0:
				Initialization.saveData(users, DATA_FILE_NAME);
				Reports.exit();
			default:
				System.out.println("Введено неверное значение! Повторите ввод:");
				break;
			}
		}
	}

	private static void workWithUser(Client client) {
		boolean toAutorization = false;
		System.out.println("Добро пожаловать. Вы вошли как клиент!");
		while (true) {

			// Выход в меню авторизации
			if (toAutorization)
				break;

			// Выводит меню для клиента
			Reports.clientMenu();

			// Выбранная нами карточка
			CreditCard currentCard;

			switch (ManagerUtils.getInputNumber()) {
			case 1:

				// выводит список карточек юзера
				Reports.getCards(client);

				currentCard = (CreditCard) ManagerUtils.getCard(client);
				if (currentCard != null) {

					if (ManagerUtils.cardIsBlocked(currentCard)) {
						System.out.println("Карточка заблокирована!");
						break;
					}
					System.out.println("-----------------------");
					System.out.println("Введите сумму:");
					System.out.println("-----------------------");
					while (true) {
						if (client.topUpAccount(currentCard, ManagerUtils.getInputNumber())) {
							System.out.println("Счёт пополнен!");
							break;
						} else {
							System.out.println("Введено некорректное число! Повторите ввод:");
						}
					}
				}
				break;
			case 2:
				boolean back = false;
				Reports.getCards(client);

				currentCard = (CreditCard) ManagerUtils.getCard(client);

				if (currentCard == null)
					break;

				if (ManagerUtils.cardIsBlocked(currentCard)) {
					System.out.println("Карточка заблокирована!");
					break;
				}

				// Выводит список услуг, которые может оплатить клиент
				Reports.clientServices();

				while (true) {

					if (back)
						break;

					switch (ManagerUtils.getInputNumber()) {
					case 1:
						System.out.println("-----------------------");
						System.out.println("Введите сумму:");
						System.out.println("-----------------------");
						while (true) {
							if (ManagerUtils.payFor(client, currentCard))
								back = true;
							break;
						}
						break;
					case 2:
						System.out.println("-----------------------");
						System.out.println("Введите сумму:");
						System.out.println("-----------------------");
						while (true) {
							if (ManagerUtils.payFor(client, currentCard))
								back = true;
							break;
						}
						break;
					case 3:
						System.out.println("-----------------------");
						System.out.println("Введите сумму:");
						System.out.println("-----------------------");
						while (true) {
							if (ManagerUtils.payFor(client, currentCard))
								back = true;
							break;
						}
						break;
					case 0:
						back = true;
						break;
					default:
						System.out.println("Введено некорректное число! Повторите ввод:");
						break;
					}
				}
				break;
			case 3:

				Reports.getCards(client);

				currentCard = (CreditCard) ManagerUtils.getCard(client);

				if (currentCard != null) {
					if (ManagerUtils.cardIsBlocked(currentCard)) {
						System.out.println("Карточка уже заблокирована!");
					} else {
						client.blockCard(currentCard);
						System.out.println("Карточка успешно заблокирована!");
					}
				}
				break;
			case 4:
				Reports.getCards(client);
				break;
			case 5:
				if (ManagerUtils.saveInDocument(client)) {
					System.out.println("Информация о клиенте успешно сохранена в файл: " + client.hashCode());
				} else {
					System.out.println("Не удалось сохранить информацию о клиенте!");
				}
				break;
			case 6:
				toAutorization = true;
				break;
			case 0:
				Initialization.saveData(users, DATA_FILE_NAME);
				Reports.exit();
				break;
			default:
				System.out.println("Введено неверное значение! Повторите ввод:");
				break;
			}
		}

	}

	private static void workWithUser(Admin admin) {
		boolean exit = false;
		System.out.println("Добро пожаловать. Вы вошли как Администратор!");
		while (true) {

			// Выход в меню авторизации
			if (exit)
				break;

			// Выводит меню для администратора
			Reports.adminMenu();

			// Выбранный юзер
			User currentuser = null;

			switch (ManagerUtils.getInputNumber()) {

			case 1:
				admin.getAllUsersInSystem(users);
				break;
			case 2:

				System.out.println("Список клиентов:");
				Reports.getAllClients(users);

				currentuser = ManagerUtils.getClientFromListOfUsers(users);

				if (currentuser != null) {

					Reports.getCards(currentuser);
					AbstractCard currentCard = ManagerUtils.getCard(currentuser);

					if (currentCard != null) {
						if (ManagerUtils.cardIsBlocked(currentCard)) {
							admin.unblockCard(currentCard);
							System.out.println("Карточка успешно разблокирована!");
						} else {
							System.out.println("Ошибка! Карточка уже разблокирована!");
						}
					}
				}
				break;
			case 3:
				exit = true;
				break;
			case 0:
				Initialization.saveData(users, DATA_FILE_NAME);
				Reports.exit();
				break;
			default:
				System.out.println("Введите неверное число! Повторите ввод:");
				break;
			}

		}

	}

}
