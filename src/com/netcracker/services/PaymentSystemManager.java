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

		// users.get(0).getUserCards().add(new CreditCard(20, 1111,
		// CardStatus.ACTIVE, CardType.MAESTRO, 5));

		// System.out.println(User.getNumberOfClients());
		// System.out.println(AbstractCard.getNumberOfCards());

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
					}

					// Узнаём, кем является наш User. И запсукаем
					// переопределённый метод для клиента и админа
					if (currentUser != null) {
						if (currentUser instanceof Client) {
							workWithUser((Client) currentUser);
						} else if (currentUser instanceof Admin) {
							workWithUser((Admin) currentUser);
						}

					} else {
						System.out.println("Проверьте введённые вами данные!");
						break;
					}
				}
				break;
			case 0:
				Initialization.saveData(users, DATA_FILE_NAME);
				Reports.exit();
			default:
				System.out.println("Введено неверное значение!");
				break;
			}

		}

	}

	private static void workWithUser(Client client) {
		System.out.println("Добро пожаловать. Вы вошли как клиент!");
		while (true) {

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

					System.out.println("Введите сумму:");
					if (client.topUpAccount(currentCard, ManagerUtils.getInputNumber())) {
						System.out.println("Счёт пополнен!");
					} else {
						System.out.println("Не удалось пополнить счёт!");
					}
				}
				break;
			case 2:

				Reports.getCards(client);

				currentCard = (CreditCard) ManagerUtils.getCard(client);

				if (currentCard != null) {

					if (ManagerUtils.cardIsBlocked(currentCard)) {
						System.out.println("Карточка заблокирована!");
						break;
					}

					// Выводит список услуг, которые может оплатить клиент
					Reports.clientServices();

					int choise = ManagerUtils.getInputNumber();
					int money;
					switch (choise) {
					case 1:
						System.out.println("Введите сумму:");
						money = ManagerUtils.getInputNumber();

						if (client.replenishAccount(currentCard, money)) {
							System.out.println("Машина оплачена!");
						} else {
							System.out.println("Не удалось оплатить! Повторите операцию!");
						}
						break;
					case 2:
						System.out.println("Введите сумму:");
						money = ManagerUtils.getInputNumber();

						if (client.replenishAccount(currentCard, money)) {
							System.out.println("Квартира оплачена!");
						} else {
							System.out.println("Не удалось оплатить! Повторите операцию!");
						}
						break;
					case 3:
						System.out.println("Введите сумму:");
						money = ManagerUtils.getInputNumber();

						if (client.replenishAccount(currentCard, money)) {
							System.out.println("Стоянка оплачена!");
						} else {
							System.out.println("Не удалось оплатить! Повторите операцию!");
						}
						break;
					default:
						System.out.println("Ошибка");
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
			case 0:
				Initialization.saveData(users, DATA_FILE_NAME);
				Reports.exit();
			default:
				System.out.println("Введено неверное значение!");
				break;
			}
		}

	}

	private static void workWithUser(Admin admin) {
		System.out.println("Добро пожаловать. Вы вошли как Администратор!");
		while (true) {

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
				System.out.println("Выбирите клиента:");
				int choise = ManagerUtils.getInputNumber();
				currentuser = ManagerUtils.getUser(users, choise);

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
			case 0:
				Initialization.saveData(users, DATA_FILE_NAME);
				Reports.exit();
				break;
			default:
				System.out.println("Введите корректное число!");
				break;
			}

		}

	}

}
