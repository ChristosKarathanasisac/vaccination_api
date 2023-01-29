package com.example.vaccinationapp.apiservices;

import java.time.LocalDate;
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
	
	public boolean dateValidation(String month,String year) 
	{
		//Formatting Vallidation
		if(month.length()!=2) {return false;}
		if(year.length()!=4) {return false;}
		
		//Older dates check
		LocalDate currentdate = LocalDate.now();
		int curMonth = currentdate.getMonthValue();
		int curYear = currentdate.getYear();
		int curDay = currentdate.getDayOfMonth();
		
		//Check for older year
		if(Integer.valueOf(year)<curYear) {return false;}
		
		//Check for older month
		char temp = month.charAt(month.length()-2);
		String examinedMonth = month;
		if(temp == '0') 
		{
			examinedMonth = String.valueOf(month.charAt(month.length()-1));
		}
		if(Integer.valueOf(examinedMonth)<curMonth) 
		{
			return false;
		}
		
		return true;
	}
	public boolean dateValidation(String day,String month,String year) 
	{
		System.out.println("-------------------");
		System.out.println("Day: "+day);
		System.out.println("Month: "+month);
		System.out.println("Year: "+year);
		
		System.out.println("-------------------");
		
		//Formatting Vallidation
		if(day.length()!=2) {return false;}
		if(month.length()!=2) {return false;}
		if(year.length()!=4) {return false;}
		System.out.println("-------------------");
		System.out.println("Formating ok");
		System.out.println("-------------------");
		//Older dates check
		LocalDate currentdate = LocalDate.now();
		int curMonth = currentdate.getMonthValue();
		int curYear = currentdate.getYear();
		int curDay = currentdate.getDayOfMonth();
		
		System.out.println("Current Day: "+curDay);
		System.out.println("Current Month: "+curMonth);
		System.out.println("Current Year: "+curYear);
		//Check for older year
		System.out.println("CHECK: "+curYear+"----"+year);
		if(Integer.valueOf(year)<curYear) {return false;}
		if(Integer.valueOf(year)>curYear) {return true;}
		
		System.out.println("CHECK: "+curMonth+"----"+month);
		//Check for older month
		char temp = month.charAt(month.length()-2);
		String examinedMonth = month;
		if(temp == '0') 
		{
			examinedMonth = String.valueOf(month.charAt(month.length()-1));
		}
		System.out.println("examinedMonth: "+examinedMonth);
		if(Integer.valueOf(examinedMonth)<curMonth) 
		{
			System.out.println(examinedMonth+"<"+curMonth);
			return false;
		}
		if(Integer.valueOf(examinedMonth)>curMonth) 
		{
			return true;
		}
		
		System.out.println("CHECK: "+curDay+"----"+day);
		//Check for older day
		temp = day.charAt(day.length()-2);
		String examinedDay = day;
		if(temp == '0') 
		{
			examinedDay = String.valueOf(day.charAt(day.length()-1));
		}
		System.out.println("examinedDay: "+examinedDay);
		if(Integer.valueOf(examinedDay)<curDay) 
		{
			System.out.println(examinedDay+"<"+curDay);
			return false;
		}
		return true;
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
