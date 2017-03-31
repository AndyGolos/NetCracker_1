package com.netcracker.beans.users;

import java.util.Date;

public class Admin extends User {

	private static final long serialVersionUID = 388582222333672349L;

	public Admin(String name, String surname, int password) {
		super(name, surname, password);
	}

	public Admin(int id, String name, String surname, int password, Date dateOfBirth) {
		super(id, name, surname, password, dateOfBirth);
	}

}
