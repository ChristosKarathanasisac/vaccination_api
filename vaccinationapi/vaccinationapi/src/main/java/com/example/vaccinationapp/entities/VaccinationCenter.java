package com.example.vaccinationapp.entities;

import java.util.*;

import javax.persistence.*;
@Entity
public class VaccinationCenter {
	@Id
	private String code;
	private String address;
	@OneToMany(mappedBy="id", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Timeslot> timeslots;
	
	public VaccinationCenter(String code, String address) {
		super();
		this.code = code;
		this.address = address;
		this.timeslots = new ArrayList<Timeslot>(); 
	}
	
	public VaccinationCenter() {
		
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

	public List<Timeslot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(List<Timeslot> timeslots) {
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
