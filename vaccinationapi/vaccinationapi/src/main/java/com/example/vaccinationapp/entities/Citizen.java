package com.example.vaccinationapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Citizen {
	@Id
	private String amka;
	private String firstName;
	private String lastName;
	private String afm;
	private String email;
	
	public Citizen() {
		
	}

	

	public Citizen(String amka, String firstName, String lastName, String afm, String email) {
		super();
		this.amka = amka;
		this.firstName = firstName;
		this.lastName = lastName;
		this.afm = afm;
		this.email = email;
	}



	public String getAmka() {
		return amka;
	}

	public void setAmka(String amka) {
		this.amka = amka;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAfm() {
		return afm;
	}

	public void setAfm(String afm) {
		this.afm = afm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
