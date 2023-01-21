package com.example.vaccinationapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Timeslot {
	@Id
	private String id;
	private String day;
	private String month;
	private String year;
	private String startHour;
	private String startMin;
	private String endHour;
	private String endMin;
	private boolean available;
	@ManyToOne
	@JoinColumn(name="vaccinationCenter_name")
	private VaccinationCenter vacCenter;
	@ManyToOne
	@JoinColumn(name="doctor_amka")
	private Doctor doc;
	
	
	public Timeslot() {
		
	}
	public String getId() {
		return id;
	}
	
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
	public VaccinationCenter getVacCenter() {
		return vacCenter;
	}
	public void setVacCenter(VaccinationCenter vacCenter) {
		this.vacCenter = vacCenter;
	}
	public void setVacCenterCode(String vacCenterCode) {
		this.vacCenter.setCode(vacCenterCode);
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Timeslot(String day, String month, String year, String startHour, String startMin, String endHour,
			String endMin,Doctor doctor,VaccinationCenter vCenter) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.startHour = startHour;
		this.startMin = startMin;
		this.endHour = endHour;
		this.endMin = endMin;
		this.doc = doctor;
		this.vacCenter = vCenter;
		this.available=true;
		//this.id = vCenter.getCode()+doctor.getAmka()+day+month+year+startHour+startMin;
	}
	
	public void createId() 
	{
		this.id = this.vacCenter.getCode()+this.doc.getAmka()+day+month+year+startHour+startMin;
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
	        /*return (this.day.equals(t.getDay())) &&(this.month.equals(t.getMonth())) &&(this.year.equals(t.getYear())) &&
	        		(this.startHour.equals(t.getStartHour())) && (this.startMin.equals(t.getStartMin())) &&(this.endHour.equals(t.getEndHour())
	        				&&(this.endMin.equals(t.getEndMin())) &&(this.doc.equals(t.getDoc()))
	        				&&this.getVacCenter().getCode().equals(t.getVacCenter().getCode()));*/
	        return this.id.equals(t.getId());
	        		
	    }
	
	public boolean isMatchingToSearch(String day,String month,String year,String vacCenterCode) 
	{
		if(!this.vacCenter.getCode().equals(vacCenterCode)) 
		{
			return false;
		}
		if(!this.year.equals(year)) 
		{
			return false;
		}
		if(!this.month.equals(month)) 
		{
			return false;
		}
		if(!this.day.equals(day)) 
		{
			return false;
		}
		return true;
	} 
	public boolean isMatchingToSearch(String month,String year,String vacCenterCode) 
	{
		if(!this.vacCenter.getCode().equals(vacCenterCode)) 
		{
			return false;
		}
		if(!this.year.equals(year)) 
		{
			return false;
		}
		if(!this.month.equals(month)) 
		{
			return false;
		}
		return true;
	} 

}
