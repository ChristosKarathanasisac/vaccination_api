package com.example.vaccinationapp.entities;

public class Citizen extends User {
	private String afm;
	private String email;
	
	public Citizen(String amka, String firstName, String lastName,String afm,String email) {
		super(amka, firstName, lastName);
		this.afm = afm;
		this.email = email;
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
