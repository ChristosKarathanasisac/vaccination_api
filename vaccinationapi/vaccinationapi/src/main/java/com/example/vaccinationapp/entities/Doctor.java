package com.example.vaccinationapp.entities;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Doctor {
	@Id
	private String amka;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy="id", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Timeslot> timeslots;

	@OneToMany(mappedBy="id", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Vaccination> vaccinations;
	

	
	public Doctor(String amka, String firstName, String lastName) {
		super();
		this.amka = amka;
		this.firstName = firstName;
		this.lastName = lastName;
		this.timeslots = new ArrayList<Timeslot>();
	}



	public Doctor() {
		
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
