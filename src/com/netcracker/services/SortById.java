package com.netcracker.services;

import com.netcracker.beans.users.User;

public class SortById implements Comparable<User> {

	private Integer id;

	public SortById(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(User o) {
		return id.compareTo(o.getId());
	}

}
