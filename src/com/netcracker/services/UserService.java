package com.netcracker.services;

import java.util.List;

import com.netcracker.beans.users.User;
import com.netcracker.dao.UserDao;

public class UserService {

	private UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public User getUser(List<User> users, int index) {
		return userDao.getUser(users, index);
	}

	public User getClient(List<User> users) {
		return userDao.getClient(users);
	}

	public User getUser(String name, String surname, int password) {
		return userDao.getUser(name, surname, password);
	}

}
