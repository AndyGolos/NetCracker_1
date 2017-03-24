package com.netcracker.services;

import java.util.HashSet;
import java.util.Set;

public class IdGenerator {

	private IdGenerator() {
	}

	public static int getIdForUser() {

		return getId(10000, IdUserSet.getIdUserSet());
	}

	public static int getIdForCard() {

		return getId(100000, IdCardSet.getIdCardSet());
	}

	public static int getId(int multiplier, Set<Integer> set) {
		if (set == null) {
			set = new HashSet<>();
		}
		Integer randomId = (int) Math.floor(Math.random() * multiplier);
		if (randomId < multiplier / 10) {
			randomId += (multiplier / 10);
		}
		if (set.contains(new Integer(randomId))) {
			return getId(multiplier, set);
		} else {
			set.add(randomId);
			return randomId;
		}
	}

}
