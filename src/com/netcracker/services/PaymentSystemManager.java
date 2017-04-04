package com.netcracker.services;

import java.util.ArrayList;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;
import com.netcracker.beans.users.Admin;
import com.netcracker.beans.users.Client;
import com.netcracker.beans.users.User;
import com.netcracker.exceptions.UserNotFoundException;
import com.netcracker.utils.IOUtils;

public class PaymentSystemManager {

	public static List<User> users = new ArrayList<>();
	public static final String DATA_FILE_NAME = "src/com/netcracker/resources/inputfiles/inputdata.txt";

	private PaymentSystemManager() {
	}

	public static void startMenu() {

		// Инициализация данных из файла
		users = Initialization.dataInitialization(DATA_FILE_NAME);

		// Если я явно не возвращаю юзеров - то мой лист пустой, почему?
		// System.out.println(users);

		System.out.println("Добрый день");
		while (true) {
			Reports.startMenu();
			switch (IOUtils.getInputNumber()) {
			case 1:
				while (true) {

					System.out.println("-----------------------");
					System.out.println("Введите имя:");
					String name = IOUtils.getInputString();
					System.out.println("Введите фамилию:");
					String surname = IOUtils.getInputString();
					System.out.println("Введите пароль:");
					int password = IOUtils.getInputNumber();
					System.out.println("-----------------------");

					User currentUser = null;
					UserService userService = new UserService();

					try {
						currentUser = userService.getUser(name, surname, password);
					} catch (UserNotFoundException e) {
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
				DocumentService.saveData(users, DATA_FILE_NAME);
				Reports.exit();
			default:
				System.out.println("Введено неверное значение! Повторите ввод:");
				break;
			}
		}
	}

	private static void workWithUser(Client client) {

		UserService clientService = new ClientService();
		CardService cardService = new CardService();

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

			switch (IOUtils.getInputNumber()) {
			case 1:

				// выводит список карточек юзера
				Reports.getCards(client);

				currentCard = (CreditCard) cardService.getCard(client);
				if (currentCard != null) {

					if (cardService.cardIsBlocked(currentCard)) {
						System.out.println("Карточка заблокирована!");
						break;
					}
					System.out.println("-----------------------");
					System.out.println("Введите сумму:");
					System.out.println("-----------------------");
					while (true) {
						if (((ClientService) clientService).topUpAccount(currentCard, IOUtils.getInputNumber())) {
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

				currentCard = (CreditCard) cardService.getCard(client);

				if (currentCard == null)
					break;

				if (cardService.cardIsBlocked(currentCard)) {
					System.out.println("Карточка заблокирована!");
					break;
				}

				// Выводит список услуг, которые может оплатить клиент
				Reports.clientServices();

				while (true) {

					if (back)
						break;

					switch (IOUtils.getInputNumber()) {
					case 1:
						System.out.println("-----------------------");
						System.out.println("Введите сумму:");
						System.out.println("-----------------------");
						while (true) {
							if (((ClientService) clientService).payFor(client, currentCard)) {
								back = true;
								break;
							}
						}
						break;
					case 2:
						System.out.println("-----------------------");
						System.out.println("Введите сумму:");
						System.out.println("-----------------------");
						while (true) {
							if (((ClientService) clientService).payFor(client, currentCard))
								back = true;
							break;
						}
						break;
					case 3:
						System.out.println("-----------------------");
						System.out.println("Введите сумму:");
						System.out.println("-----------------------");
						while (true) {
							if (((ClientService) clientService).payFor(client, currentCard))
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

				currentCard = (CreditCard) cardService.getCard(client);

				if (currentCard != null) {
					if (cardService.cardIsBlocked(currentCard)) {
						System.out.println("Карточка уже заблокирована!");
					} else {
						((ClientService) clientService).blockCard(currentCard);
						System.out.println("Карточка успешно заблокирована!");
					}
				}
				break;
			case 4:
				Reports.getCards(client);
				break;
			case 5:
				if (DocumentService.saveCardsInDocument(client)) {
					System.out.println("Информация о клиенте успешно сохранена в файл: " + client.hashCode());
				} else {
					System.out.println("Не удалось сохранить информацию о клиенте!");
				}
				break;
			case 6:
				toAutorization = true;
				break;
			case 0:
				DocumentService.saveData(users, DATA_FILE_NAME);
				Reports.exit();
				break;
			default:
				System.out.println("Введено неверное значение! Повторите ввод:");
				break;
			}
		}

	}

	private static void workWithUser(Admin admin) {

		UserService adminService = new AdminService();

		CardService cardService = new CardService();
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

			switch (IOUtils.getInputNumber()) {

			case 1:
				((AdminService) adminService).getAllUsersInSystem(users);
				break;
			case 2:
				((AdminService) adminService).sortUsersById();
				break;
			case 3:

				System.out.println("Список клиентов:");
				Reports.getAllClients(users);

				currentuser = adminService.getClient(users);

				if (currentuser != null) {

					Reports.getCards(currentuser);
					AbstractCard currentCard = cardService.getCard(currentuser);

					if (currentCard != null) {
						if (cardService.cardIsBlocked(currentCard)) {
							((AdminService) adminService).unblockCard(currentCard);
							System.out.println("Карточка успешно разблокирована!");
						} else {
							System.out.println("Ошибка! Карточка уже разблокирована!");
						}
					}
				}
				break;
			case 4:
				exit = true;
				break;
			case 0:
				DocumentService.saveData(users, DATA_FILE_NAME);
				Reports.exit();
				break;
			default:
				System.out.println("Введите неверное число! Повторите ввод:");
				break;
			}

		}

	}

}
