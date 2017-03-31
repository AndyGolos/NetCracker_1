package com.netcracker.interfaces;

import com.netcracker.beans.cards.CreditCard;

public interface ReplenishAction {

	boolean replenishAccount(CreditCard card, int money); // Совершить платёж

}
