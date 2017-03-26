package com.netcracker.beans.users;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.netcracker.beans.cards.AbstractCard;

public class User implements Serializable {

	private static final long serialVersionUID = 7604616806259567350L;

	private int id;
	private String name;
	private String surname;
	private int password;
	private List<AbstractCard> userCards;
	private String registrationDate;
	private Date dateOfBirth;
	private static int numberOfClients = 0;

	// Для компоратора!
	public User(String name, String surname, int password) {
		this.name = name;
		this.surname = surname;
		this.password = password;
	}

	public User(int id, String name, String surname, int password, Date dateOfBirth) {
		this(name, surname, password);
		this.id = id;
		this.userCards = new ArrayList<>();
		this.registrationDate = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date());
		this.dateOfBirth = dateOfBirth;
		numberOfClients++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + password;
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((userCards == null) ? 0 : userCards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password != other.password)
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userCards == null) {
			if (other.userCards != null)
				return false;
		} else if (!userCards.equals(other.userCards))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id + ",\t имя=" + name + ",\t\t фамилия=" + surname + ",\t дата регистрации=" + registrationDate
				+ ",\t дата рождения=" + dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public List<AbstractCard> getUserCards() {
		return userCards;
	}

	public void setUserCards(List<AbstractCard> userCards) {
		this.userCards = userCards;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public static int getNumberOfClients() {
		return numberOfClients;
	}

}
