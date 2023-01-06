package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.Citizen;
import com.example.vaccinationapp.entities.Vaccination;

@Service
public class VaccinationService {
ArrayList<Vaccination> vaccinations;

public void InitServiceForTestOnly() 
{
	this.vaccinations = new ArrayList<Vaccination>();
}
public void addVaccination(Vaccination vaccination) 
{
	this.vaccinations.add(vaccination);
}
public Vaccination getVaccinationByCitizen(Citizen citizen) 
{
	for(Vaccination v:this.vaccinations) 
	{
		if(v.getCitizen().equals(citizen)) 
		{
			return v;
		}
	}
	return null;
	
}
public boolean checkIfVaccinationExist(Vaccination vaccination) 
{
	for(Vaccination v:this.vaccinations) 
	{
		if(v.equals(vaccination)) 
		{
			return true;
		}
	}
	return false;
}
}
