package com.example.vaccinationapp.entities;

public abstract class User {
	private String amka;
	private String firstName;
	private String lastName;
	
	public User(String amka, String firstName, String lastName) {
		//super();
		this.amka = amka;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

	public String getAmka() {
		return amka;
	}
	public void setAmka(String amka) {
		this.amka = amka;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	 public boolean equals(Object o) {
		  
	        if (o == this) {
	            return true;
	        }
	 
	        if (!(o instanceof User)) {
	            return false;
	        }
	        User u= (User) o;
	        return (this.amka.equals(u.getAmka()));
	    }
}
