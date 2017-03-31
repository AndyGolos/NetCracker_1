package com.netcracker.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.beans.users.User;

public class Initialization {

	private Initialization() {
	}

	@SuppressWarnings("unchecked")
	public static List<User> dataInitialization(String dataFileName) {

		List<User> users = new ArrayList<>();

		if (!new File(dataFileName).exists()) {
			StaticDataInput.dataInitialization(users, dataFileName);
		}

		File file = new File(dataFileName);

		try (FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {

			users = ((List<User>) inputStream.readObject());
			return users;

		} catch (FileNotFoundException e) {
			System.out.println("Не найден файл: " + dataFileName);
			System.exit(0);
			return null;
		} catch (IOException e) {
			System.out.println("IOException");
			System.exit(0);
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Не удалось привести к List<User>");
			System.exit(0);
			return null;
		}
	}
}
