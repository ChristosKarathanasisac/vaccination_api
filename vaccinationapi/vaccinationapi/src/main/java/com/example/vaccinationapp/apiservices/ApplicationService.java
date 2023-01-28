package com.example.vaccinationapp.apiservices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.repositories.*;

@Service
public class ApplicationService {
	@Autowired
	private VaccinationCenterRepository vaccinationCenterRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private CitizenRepository citizenRepository;
	
	public void addVaccinationCenter(VaccinationCenter v) 
	{
		vaccinationCenterRepository.save(v);
	}
	
	public void addDoctor(Doctor d) 
	{
		doctorRepository.save(d);
	}
	
	public void addCitizen(Citizen c) 
	{
		citizenRepository.save(c);
	}
	
	public Citizen getCitizenByAMKA(String amka) 
	{
		Optional<Citizen> byId = citizenRepository.findById(amka);
		if(byId.isPresent()) 
		{
			return byId.get();
		}
		return null;
			
	}
	public Doctor getDoctorByAMKA(String amka) 
	{
		Optional<Doctor> byId = doctorRepository.findById(amka);
		if(byId.isPresent()) 
		{
			return byId.get();
		}
		return null;
			
	}
	public List<VaccinationCenter> getVaccinationCenters()
	{
		return this.vaccinationCenterRepository.findAll();
	}

}
