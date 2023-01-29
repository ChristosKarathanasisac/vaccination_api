package com.example.vaccinationapp.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Citizen {
	@Id
	private String amka;
	@JsonIgnore
	private String firstName;
	@JsonIgnore
	private String lastName;
	@JsonIgnore
	private String password;
	



	@JsonIgnore
	private String afm;
	@JsonIgnore
	private String email;
	
	
	@OneToOne(mappedBy = "citizen", fetch = FetchType.LAZY,orphanRemoval = false)
	@JsonIgnore
	private Appointment appointment;
	
	@OneToOne(mappedBy = "citizen", fetch = FetchType.LAZY,orphanRemoval = false)
	private Vaccination vaccination;
	
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



	public void setPassword(String password) {
		this.password = password;
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



	public Appointment getAppointment() {
		return appointment;
	}

	public String getPassword() {
		return password;
	}

	public Vaccination getVaccination() {
		return vaccination;
	}

}
