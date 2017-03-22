package com.netcracker.services;

import java.util.HashSet;
import java.util.Set;

public class IdUserSet {

	private IdUserSet() {
	}

	private static class UserSet {
		private static final Set<Integer> INSTANCE = new HashSet<>();
	}

	public static Set<Integer> getIdUserSet() {
		return UserSet.INSTANCE;
	}

}
