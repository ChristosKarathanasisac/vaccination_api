package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.repositories.TimeslotRepository;
import com.example.vaccinationapp.repositories.VaccinationCenterRepository;



@Service
public class TimeslotsService {
	@Autowired
	TimeslotRepository timeslotRepository;
	public TimeslotsService() {
	}
	
	
	public ArrayList<Timeslot> getTimeslotsByDate(String day,String month,String year,String vacCenterCode) 
	{
		ArrayList<Timeslot> timeslotsFilteredByDate = new ArrayList<Timeslot>();
		for(Timeslot t:this.timeslotRepository.findAll()) 
		{
			if(t.isMatchingToSearch(day, month, year, vacCenterCode)) 
			{
				timeslotsFilteredByDate.add(t);
			}
		}
		return timeslotsFilteredByDate;
	}
	
	public void addTimeslot(Timeslot timeslot) 
	{
		System.out.println("Inside addTimeslot");
		timeslotRepository.save(timeslot);
		System.out.println("Timeslot Added");
	}
	
	
	public boolean removeTimeslot(Long id) 
	{
		if(timeslotRepository.findById(id)!=null) 
		{
			timeslotRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public boolean checkIfTimeslotAlreadyExist(Timeslot timeslot) 
	{
		/*
		VaccinationCenter vc = getVaccinationCenterByCode(timeslot.getVacCenter().getCode());
		for(Timeslot t:vc.getTimeslots()) 
		{
			if(t.equals(timeslot)) 
			{
				return true;
			}
		}
		return false;
		*/
		return false;
	}
	public ArrayList<Timeslot> getTimeslotsByMonth(String month,String year,String vacCenterCode)
	{
		ArrayList<Timeslot> timeslotsFilteredByMonth = new ArrayList<Timeslot>();
		for(Timeslot t:this.timeslotRepository.findAll()) 
		{
			if(t.isMatchingToSearch(month, year, vacCenterCode)) 
			{
				timeslotsFilteredByMonth.add(t);
			}
		}
		return timeslotsFilteredByMonth;
	}
	public Timeslot getTimeslotById(Long id) 
	{
		Optional<Timeslot> byId = timeslotRepository.findById(id);
		if(byId.isPresent()) 
		{
			return byId.get();
		}
		return null;
	}
	public void updateTimesotStatus(Long id,boolean status) 
	{
		Timeslot t = getTimeslotById(id);
		t.setAvailable(status);
		timeslotRepository.save(t);
	}
}
