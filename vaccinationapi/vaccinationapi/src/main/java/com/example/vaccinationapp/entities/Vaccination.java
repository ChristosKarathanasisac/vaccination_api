package com.example.vaccinationapp.entities;

import java.util.Date;

import javax.persistence.*;
@Entity
public class Vaccination {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen",referencedColumnName = "amka")
	private Citizen citizen;
	
	@ManyToOne
	@JoinColumn(name="doctor_amka")
	private Doctor doctor;
	
	private Date vaccinationDate;
	private Date vaccinationEndDate;
	
	
	public Vaccination(Citizen citizen, Doctor doctor, Date vaccinationDate, Date vaccinationEndDate) {
		super();
		this.citizen = citizen;
		this.doctor = doctor;
		this.vaccinationDate = vaccinationDate;
		this.vaccinationEndDate = vaccinationEndDate;
	}
	public Vaccination() {
	}
	public Citizen getCitizen() {
		return citizen;
	}
	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getVaccinationDate() {
		return vaccinationDate;
	}
	public void setVaccinationDate(Date vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}
	public Date getVaccinationEndDate() {
		return vaccinationEndDate;
	}
	public void setVaccinationEndDate(Date vaccinationEndDate) {
		this.vaccinationEndDate = vaccinationEndDate;
	}
	@Override
	 public boolean equals(Object o) {
		  
	        if (o == this) {
	            return true;
	        }
	 
	        if (!(o instanceof Vaccination)) {
	            return false;
	        }
	        Vaccination v = (Vaccination) o;
	        return (this.getCitizen().equals(v.getCitizen())) && (this.vaccinationDate.equals(v.getVaccinationDate()));
	        		
	    }
	
}
