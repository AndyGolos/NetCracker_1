package com.netcracker.services;

import java.util.HashSet;
import java.util.Set;

public class IdCardSet {

	private IdCardSet() {
	}

	private static class CardSet {
		private static final Set<Integer> INSTANCE = new HashSet<>();
	}

	public static Set<Integer> getIdCardSet() {
		return CardSet.INSTANCE;
	}

}
