package com.netcracker.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class IOUtils {

	private static Scanner scanner;

	private IOUtils() {
	}

	public static int getInputNumber() {
		int choise = -1;
		while (choise < 0) {
			try {
				scanner = new Scanner(System.in);
				choise = scanner.nextInt();
				if (choise >= 0) {
					break;
				} else {
					System.out.println("Введено некорректное число! Повторите ввод:");
				}
			} catch (InputMismatchException e) {
				System.out.println("Введите корректные данные!");
			}
		}
		return choise;
	}

	public static String getInputString() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

}
