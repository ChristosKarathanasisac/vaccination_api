package com.example.vaccinationapp.requestclasses;


public class RequestBookVaccination {
	private Long tmslot_id;
	private String citizen_amka;
	
	public RequestBookVaccination() {
	}

	public Long getTmslot_id() {
		return tmslot_id;
	}

	public void setTmslot_id(Long tmslot_id) {
		this.tmslot_id = tmslot_id;
	}

	public String getCitizen_amka() {
		return citizen_amka;
	}

	public void setCitizen_amka(String citizen_amka) {
		this.citizen_amka = citizen_amka;
	}

	public RequestBookVaccination(Long tmslot_id, String citizen_amka) {
		super();
		this.tmslot_id = tmslot_id;
		this.citizen_amka = citizen_amka;
	}
	
	
	
	
}
