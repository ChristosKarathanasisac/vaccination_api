package com.example.vaccinationapp.requestclasses;


public class RequestBookAppointment {
	private Long tmslotID;
	private String citizenAMKA;
	
	public RequestBookAppointment() {
	}

	public Long getTmslotID() {
		return tmslotID;
	}

	public void setTmslotID(Long tmslot_id) {
		this.tmslotID = tmslot_id;
	}

	public String getCitizenAMKA() {
		return citizenAMKA;
	}

	public void setCitizenAMKA(String citizen_amka) {
		this.citizenAMKA = citizen_amka;
	}

	public RequestBookAppointment(Long tmslot_id, String citizen_amka) {
		super();
		this.tmslotID = tmslot_id;
		this.citizenAMKA = citizen_amka;
	}
	
	
	
	
}
