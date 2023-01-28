package com.example.vaccinationapp.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Vaccination {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "citizen",referencedColumnName = "amka")
	@JsonIgnore
	private Citizen citizen;
	
	@ManyToOne
	@JoinColumn(name="doctor_amka")
	@JsonIgnore
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
	
	public Long getId() {
		return id;
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
	        return this.id == v.getId();
	        		
	    }
	
}
