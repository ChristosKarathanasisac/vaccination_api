package com.example.vaccinationapp.entities;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Doctor {
	@Id
	private String amka;
	@JsonIgnore
	private String firstName;
	@JsonIgnore
	private String lastName;
	@JsonIgnore
	private String Password;
	
	@OneToMany(mappedBy="id", cascade= CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	//@JsonIgnore
	private List<Timeslot> timeslots;

	@OneToMany(mappedBy="id", cascade= CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	@JsonIgnore
	private List<Vaccination> vaccinations;
	

	
	public Doctor(String amka, String firstName, String lastName) {
		super();
		this.amka = amka;
		this.firstName = firstName;
		this.lastName = lastName;
		this.timeslots = new ArrayList<Timeslot>();
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}



	public Doctor() {
		
	}


	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof Doctor)) {
			return false;
		}
		Doctor d = (Doctor) o;
		return this.amka == d.getAmka();

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



	public List<Timeslot> getTimeslots() {
		return timeslots;
	}



	public void setTimeslots(List<Timeslot> timeslots) {
		this.timeslots = timeslots;
	}

	public void addTimeslot(Timeslot t) {
		this.timeslots.add(t);
	}

	
}
