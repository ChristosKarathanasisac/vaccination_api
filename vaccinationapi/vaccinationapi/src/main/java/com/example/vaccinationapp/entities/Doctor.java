package com.example.vaccinationapp.entities;

import java.util.ArrayList;

public class Doctor extends User {
	private ArrayList<Timeslot> timeslots;

	

	public Doctor(String amka, String firstName, String lastName) {
		super(amka, firstName, lastName);
		this.timeslots = new ArrayList<Timeslot>();
	}

	public ArrayList<Timeslot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(ArrayList<Timeslot> timeslots) {
		this.timeslots = timeslots;
	}

	
}
