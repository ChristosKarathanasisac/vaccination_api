package com.example.vaccinationapp.entities;

import java.sql.Time;
import java.util.Date;

public class Timeslot {
	private String day;
	private String month;
	private String year;
	private String startHour;
	private String startMin;
	private String endHour;
	private String endMin;
	private String vaccinationCenterCode;
	private Doctor doc;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
	public Doctor getDoc() {
		return doc;
	}
	public void setDoc(Doctor doc) {
		this.doc = doc;
	}
	
	public Timeslot(String day, String month, String year, String startHour, String startMin, String endHour,
			String endMin,Doctor doctor,String vacCenterCode) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.startHour = startHour;
		this.startMin = startMin;
		this.endHour = endHour;
		this.endMin = endMin;
		this.doc = doctor;
		this.vaccinationCenterCode = vacCenterCode;
	}
	
	@Override
	 public boolean equals(Object o) {
		  
	        if (o == this) {
	            return true;
	        }
	 
	        if (!(o instanceof Timeslot)) {
	            return false;
	        }
	        Timeslot t = (Timeslot) o;
	        return (this.day.equals(t.getDay())) &&(this.month.equals(t.getMonth())) &&(this.year.equals(t.getYear())) &&
	        		(this.startHour.equals(t.getStartHour())) && (this.startMin.equals(t.getStartMin())) &&(this.endHour.equals(t.getEndHour())
	        				&&(this.endMin.equals(t.getEndMin())) &&(this.doc.equals(t.getDoc()))
	        				&&this.vaccinationCenterCode.endsWith(t.getVaccinationCenterCode()));
	        		
	    }
	public String getVaccinationCenterCode() {
		return vaccinationCenterCode;
	}
	public void setVaccinationCenterCode(String vaccinationCenterCode) {
		this.vaccinationCenterCode = vaccinationCenterCode;
	}

}
