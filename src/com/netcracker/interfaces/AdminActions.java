package com.netcracker.interfaces;

import java.util.List;

import com.netcracker.beans.cards.AbstractCard;
import com.netcracker.beans.users.User;

public interface AdminActions {

	void getAllUsersInSystem(List<User> users); // получить список всех юзеров

	boolean unblockCard(AbstractCard card); // Разблокировать карточку

}
