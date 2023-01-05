package com.example.vaccinationapp.entities;

import java.util.ArrayList;

public class VaccinationCenter {
	private String code;
	private String address;
	private ArrayList<Timeslot> timeslots;
	
	public VaccinationCenter(String code, String address) {
		super();
		this.code = code;
		this.address = address;
		this.timeslots = new ArrayList<Timeslot>(); 
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<Timeslot> getTimeslots() {
		return timeslots;
	}
	public void setTimeslots(ArrayList<Timeslot> timeslots) {
		this.timeslots = timeslots;
	}
	public void addTimeslot(Timeslot t) 
	{
		this.timeslots.add(t);
	}
	public void removeTimeslot(Timeslot t) 
	{
		this.timeslots.remove(t);
	}
	

}
