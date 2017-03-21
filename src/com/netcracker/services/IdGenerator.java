package com.netcracker.services;

import java.util.HashSet;
import java.util.Set;

public class IdGenerator {

	private static Set<Integer> idUserSet;
	private static Set<Integer> idCardSet;

	private IdGenerator() {
	}

	public static int getIdForUser() {
		if (idUserSet == null) {
			idUserSet = new HashSet<>();
		}

		Integer randomId = (int) Math.floor(Math.random() * 10000);
		if (randomId < 1000) {
			randomId += 1000;
		}
		if (idUserSet.contains(new Integer(randomId))) {
			return getIdForUser();
		} else {
			idUserSet.add(randomId);
			return randomId;
		}
	}

	public static int getIdForCard() {
		if (idCardSet == null) {
			idCardSet = new HashSet<>();
		}

		Integer randomId = (int) Math.floor(Math.random() * 100000);
		if (randomId < 10000) {
			randomId += 10000;
		}
		if (idCardSet.contains(new Integer(randomId))) {
			return getIdForCard();
		} else {
			idCardSet.add(randomId);
			return randomId;
		}
	}
}
