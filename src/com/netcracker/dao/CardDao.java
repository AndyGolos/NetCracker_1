package com.netcracker.dao;

import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.users.User;
import com.netcracker.utils.IOUtils;

public class CardDao {

	public AbstractCard getCard(List<AbstractCard> listOfUserCards, int index) {
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

	public AbstractCard getCard(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		int choise;
		System.out.println("-----------------------");
		System.out.println("Введите номер карточки:");
		System.out.println("-----------------------");
		while (true) {
			choise = IOUtils.getInputNumber();
			if (choise > user.getUserCards().size() || choise == 0) {
				System.out.println("Введено некорректное число! Повторите ввод:");
				continue;
			} else
				break;
		}
		return getCard(user.getUserCards(), choise);
	}

}
