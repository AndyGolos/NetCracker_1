package com.netcracker.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.netcracker.beans.users.User;

public class DocumentService {

	private final static String ROOT_DIRECTORY_PATH = "src/com/netcracker/resources/outputfiles/";
	private final static String FILE_EXTENTION = ".txt";

	private DocumentService() {
	}

	public static boolean saveCardsInDocument(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		try (FileWriter writer = new FileWriter(new File(ROOT_DIRECTORY_PATH + user.hashCode() + FILE_EXTENTION))) {

			user.getUserCards().forEach(card -> {
				try {
					writer.write(card + "\n");
				} catch (IOException e) {
					System.out.println("IOException in inner saveInDocument!");
				}
			});

			return true;

		} catch (IOException e) {
			System.out.println("IOException in outer saveInDocument!");
			return false;
		}
	}

	public static void saveData(List<User> users, String dataFileName) {

		if (new File(dataFileName).exists()) {
			new File(dataFileName).delete();
		}

		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(dataFileName)))) {

			outputStream.writeObject(users);

		} catch (FileNotFoundException e) {
			System.out.println("Не найден файл: " + dataFileName);
		} catch (IOException e) {
			System.out.println("IOException in saveData");
		}

	}

}
