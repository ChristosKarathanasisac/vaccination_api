package com.example.vaccinationapp.apiservices;

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

}
