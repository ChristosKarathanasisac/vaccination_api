package com.example.vaccinationapp.apiservices;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public void removeVaccination(Long id) 
{
	this.vaccinationRepository.deleteById(id);
}
public Vaccination getVaccinationByCitizen(String amka) 
{
	for(Vaccination v:vaccinationRepository.findAll()) 
	{
		if(v.getCitizen().getAmka().equals(amka)) 
		{
			if(v.getVaccinationEndDate().after(new Date())) 
			{
				return v;
			}
		}
	}
	return null;
	
}

public Vaccination getInvalidVaccinationByCitizen(String amka) 
{
	for(Vaccination v:vaccinationRepository.findAll()) 
	{
		if(v.getCitizen().getAmka().equals(amka)) 
		{
			if(v.getVaccinationEndDate().before(new Date())) 
			{
				return v;
			}
		}
	}
	return null;
	
}


}
