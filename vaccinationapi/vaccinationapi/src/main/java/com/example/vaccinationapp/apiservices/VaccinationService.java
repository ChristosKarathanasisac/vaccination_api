package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.Citizen;
import com.example.vaccinationapp.entities.Vaccination;
import com.example.vaccinationapp.repositories.VaccinationRepository;

@Service
public class VaccinationService {
@Autowired
VaccinationRepository vaccinationRepository;


public void addVaccination(Vaccination vaccination) 
{
	vaccinationRepository.save(vaccination);
}
public Vaccination getVaccinationByCitizen(String amka) 
{
	for(Vaccination v:vaccinationRepository.findAll()) 
	{
		if(v.getCitizen().getAmka().equals(amka)) 
		{
			return v;
		}
	}
	return null;
	
}
/*public boolean checkIfVaccinationExist(Vaccination vaccination) 
{
	for(Vaccination v:this.vaccinations) 
	{
		if(v.equals(vaccination)) 
		{
			return true;
		}
	}
	return false;
}*/
}
