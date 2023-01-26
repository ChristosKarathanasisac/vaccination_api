package com.example.vaccinationapp.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Αppointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	//@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "address_id", referencedColumnName = "id")
	//@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = false)
	//@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name = "citizen_amka")
    //@JoinColumn(name = "citizen_amka",referencedColumnName = "amka")
	private Citizen citizen;
	
	//@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "timeslot",referencedColumnName = "id")
	private Timeslot timeslot;
	@JsonIgnore
	private int changes;
	@JsonIgnore
	private boolean active;
	
	public Αppointment() {
		
	}
	public Αppointment(Citizen citizen, Timeslot timeslot) {
		super();
		this.citizen = citizen;
		this.timeslot = timeslot;
		this.changes = 2;
		this.active  =true;
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
	
	public boolean isActive() {
		return active;
	}
	
	
	public void setActive(boolean available) {
		this.active = available;
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
	        if (!(o instanceof Αppointment)) {
	            return false;
	        }
	        Αppointment a= (Αppointment) o;
	        return (this.getId() == a.getId());
	    }
}
