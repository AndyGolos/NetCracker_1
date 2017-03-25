package com.netcracker.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.netcracker.beans.users.User;

public class Initialization {

	private Initialization() {
	}

	public static void saveData(List<User> users, String dataFileName) {

		// Если файл существует, то мы удаляем его, а затем заново создаём и
		// пишем в него новую инфу
		if (new File(dataFileName).exists()) {
			new File(dataFileName).delete();
		}

		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(dataFileName)))) {

			outputStream.writeObject(users);

		} catch (FileNotFoundException e) {
			System.out.println("Не найден файл: " + dataFileName);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("IOException in saveData");
			System.exit(0);
		}

	}

	@SuppressWarnings("unchecked")
	public static List<User> dataInitialization(List<User> users, String dataFileName) {

		if (!new File(dataFileName).exists()) {
			StaticDataInput.dataInitialization(users, dataFileName);
		}

		FileInputStream fileInputStream = null;
		ObjectInputStream inputStream = null;

		try {
			File file = new File(dataFileName);
			fileInputStream = new FileInputStream(file);
			inputStream = new ObjectInputStream(fileInputStream);
			users = ((List<User>) inputStream.readObject());

			// System.out.println(users);

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
		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.out.println("Не удалось закрыть inputStream");
				}
			}

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e2) {
					System.out.println("Не удалось закрыть fileInputStream");
				}
			}

		}

	}

}
