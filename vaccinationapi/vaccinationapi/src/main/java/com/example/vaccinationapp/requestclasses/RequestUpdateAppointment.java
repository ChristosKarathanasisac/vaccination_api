package com.example.vaccinationapp.requestclasses;

public class RequestUpdateAppointment {
	private Long appointmentID;
	private Long newTimeslotID;
	
	
	
	public RequestUpdateAppointment() {
		super();
	}
	public Long getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(Long appointmentID) {
		this.appointmentID = appointmentID;
	}
	public Long getNewTimeslotID() {
		return newTimeslotID;
	}
	public void setNewTimeslotID(Long newTomeslotID) {
		this.newTimeslotID = newTomeslotID;
	}

	
	
	
}
