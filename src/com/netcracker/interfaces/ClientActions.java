package com.netcracker.interfaces;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;

public interface ClientActions {

	// Легче ClientActionsImpl и там реализовать логику
	boolean topUpAccount(CreditCard card, int money); // Пополнить счёт

	boolean replenishAccount(CreditCard card, int money); // Сделать платёж

	boolean blockCard(AbstractCard card); // Заблокировать карточку

}
