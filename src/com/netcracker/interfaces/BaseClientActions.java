package com.netcracker.interfaces;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.cards.CreditCard;

public interface BaseClientActions {

	boolean topUpAccount(CreditCard card, int money); // Пополнить счёт

	boolean blockCard(AbstractCard card); // Заблокировать карточку

}
