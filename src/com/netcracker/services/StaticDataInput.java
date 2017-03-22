package com.netcracker.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;
import com.netcracker.beans.users.Admin;
import com.netcracker.beans.users.Client;
import com.netcracker.beans.users.User;
import com.netcracker.enums.CardStatus;
import com.netcracker.enums.CardType;

public class StaticDataInput {

	public static void dataInitialization(List<User> users, String fileName) {

		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(fileName)));) {

			String[] names = { "Андрей", "Сергей", "Виктор", "Мария", "Антон", "Саша", "Илья", "Татьяна", "Олег",
					"Владислав" };

			String[] surnames = { "Голосов", "Волков", "Меленский", "Киселёва", "Мараев", "Логинова", "Бутрим",
					"Мирлас", "Высоцкий", "Гапчинский" };

			String[] adminsNames = { "Эдуардо", "Максим", "Нерзул" };

			String[] adminsSurnames = { "Первый", "Второй", "Игоревич" };

			CardType[] type = { CardType.VISA, CardType.MAESTRO, CardType.MASTERCARD, CardType.VISA, CardType.MAESTRO,
					CardType.MASTERCARD, CardType.VISA, CardType.MAESTRO, CardType.MASTERCARD, CardType.VISA,
					CardType.MAESTRO, CardType.MASTERCARD, CardType.VISA, CardType.MAESTRO, CardType.MASTERCARD,
					CardType.VISA, CardType.MAESTRO, CardType.MASTERCARD, CardType.VISA, CardType.MAESTRO };

			List<AbstractCard> listOfCards;
			int cardNumber = 0;

			for (int client = 0; client < 10; client++) {
				listOfCards = new ArrayList<>();

				for (int creditCard = 0; creditCard < 2; creditCard++) {
					listOfCards.add(
							new CreditCard(IdGenerator.getIdForCard(), 1234, CardStatus.ACTIVE, type[cardNumber], 5));
					cardNumber++;
				}

				User user = new Client(IdGenerator.getIdForUser(), names[client], surnames[client], 1111, new Date());
				user.setUserCards(listOfCards);
				users.add(user);
				listOfCards = null;
			}

			for (int i = 0; i < 3; i++) {
				users.add(new Admin(IdGenerator.getIdForUser(), adminsNames[i], adminsSurnames[i], 2222, new Date()));
			}

			outputStream.writeObject(users);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
