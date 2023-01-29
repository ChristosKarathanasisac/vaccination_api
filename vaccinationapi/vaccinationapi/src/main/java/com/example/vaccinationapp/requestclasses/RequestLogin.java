package com.example.vaccinationapp.requestclasses;

public class RequestLogin {
	String amka;
	String password;
	boolean isDoctor;
	public String getAmka() {
		return amka;
	}
	public void setAmka(String amka) {
		this.amka = amka;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDoctor() {
		return isDoctor;
	}
	public void setDoctor(boolean isDoctor) {
		this.isDoctor = isDoctor;
	}
	public RequestLogin() {
		
	}

	
	
}
