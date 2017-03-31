package com.netcracker.beans.users;

import java.util.Date;

public class Client extends User {

	private static final long serialVersionUID = -5675775384827995608L;

	public Client(int id, String name, String surname, int password, Date dateOfBirth) {
		super(id, name, surname, password, dateOfBirth);
	}

}
