package com.example.vaccinationapp.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Appointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name = "citizen_amka")
	private Citizen citizen;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "timeslot",referencedColumnName = "id")
	@JsonManagedReference
	private Timeslot timeslot;
	@JsonIgnore
	private int changes;
	
	
	public Appointment() {
		
	}
	public Appointment(Citizen citizen, Timeslot timeslot) {
		super();
		this.citizen = citizen;
		this.timeslot = timeslot;
		this.changes = 2;
		//this.active  =true;
	}
	
	public Long getId() {
		return id;
	}
	public Citizen getCitizen() {
		return citizen;
	}
	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
	public Timeslot getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}
	public int getChanges() {
		return changes;
	}
	public void setChanges(int changes) {
		this.changes = changes;
	}
	
	public void removeTimeslot() 
	{
		this.timeslot = null;
	}
	@Override
	 public boolean equals(Object o) {
		  
	        if (o == this) {
	            return true;
	        }
	        if (!(o instanceof Appointment)) {
	            return false;
	        }
	        Appointment a= (Appointment) o;
	        return (this.getId() == a.getId());
	    }
}
