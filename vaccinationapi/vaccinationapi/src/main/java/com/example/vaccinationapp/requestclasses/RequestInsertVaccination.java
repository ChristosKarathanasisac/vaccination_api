package com.example.vaccinationapp.requestclasses;

import java.util.Date;

public class RequestInsertVaccination {
	String citizenAMKA;
	String doctorAMKA;
	Date date;
	public RequestInsertVaccination(String citizenAMKA, String doctorAMKA, Date date) {
		super();
		this.citizenAMKA = citizenAMKA;
		this.doctorAMKA = doctorAMKA;
		this.date = date;
	}
	public RequestInsertVaccination() {
		
	}
	public String getCitizenAMKA() {
		return citizenAMKA;
	}
	public void setCitizenAMKA(String citizenAMKA) {
		this.citizenAMKA = citizenAMKA;
	}
	public String getDoctorAMKA() {
		return doctorAMKA;
	}
	public void setDoctorAMKA(String doctorAMKA) {
		this.doctorAMKA = doctorAMKA;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
