package com.example.vaccinationapp.entities;

public class Αppointment {
	private Citizen citizen;
	private Timeslot timeslot;
	private int changes;
	
	public Αppointment(Citizen citizen, Timeslot timeslot) {
		super();
		this.citizen = citizen;
		this.timeslot = timeslot;
		this.changes = 2;
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
	@Override
	 public boolean equals(Object o) {
		  
	        if (o == this) {
	            return true;
	        }
	        if (!(o instanceof Αppointment)) {
	            return false;
	        }
	        Αppointment a= (Αppointment) o;
	        return (this.citizen.equals(a.getCitizen())) && (this.timeslot.equals(a.getTimeslot()));
	    }
}
