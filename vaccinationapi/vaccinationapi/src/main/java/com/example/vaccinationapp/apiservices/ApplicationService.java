package com.example.vaccinationapp.apiservices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	
	public boolean isRegisteredDoctor(String amka,String password) 
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	   
		Doctor doctor = getDoctorByAMKA(amka);
		if(doctor==null) 
		{
			return false;
		}
		if(encoder.matches(password, doctor.getPassword()))
		{
			return true;
		}else 
		{
			return false;
		}
	}
	
	public boolean isRegisteredCitizen(String amka,String password) 
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Citizen citizen = getCitizenByAMKA(amka);
		if(citizen==null) 
		{
			System.out.println("Citizen was null");
			return false;
		}
		if(encoder.matches(password, citizen.getPassword())) 
		{
			System.out.println("True");
			return true;
		}else 
		{
			System.out.println("False");
			return false;
		}
	}
	
	
	public void insertCitizen(Citizen aCitizen) 
	{
		this.citizenRepository.save(aCitizen);
	}
	public void insertDoctor(Doctor aDoctor) 
	{
		this.doctorRepository.save(aDoctor);
	}
}
