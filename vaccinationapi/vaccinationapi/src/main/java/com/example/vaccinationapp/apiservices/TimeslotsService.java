package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.repositories.TimeslotRepository;
import com.example.vaccinationapp.repositories.VaccinationCenterRepository;



@Service
public class TimeslotsService {
	private TimeslotRepository timeslotRepository2;
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
	public List<VaccinationCenter> getAllVaccinationCenters()
	{
		/*if(this.vaccinationCenters==null) 
		{
			return new ArrayList<VaccinationCenter>();
		}
		return this.vaccinationCenters;*/
		return null;
		
	}
	private VaccinationCenter getVaccinationCenterByCode(String vacCenterCode) 
	{
		/*if(this.vaccinationCenters == null) 
		{
			return null;
		}
		for(VaccinationCenter v:this.vaccinationCenters) 
		{
			if(v.getCode().equals(vacCenterCode)) 
			{
				return v;
			}
		}
		return null;*/
		return null;
	}
	public void removeTimeslot(Timeslot t) 
	{
		/*for(VaccinationCenter v:this.vaccinationCenters) 
		{
			for(Timeslot timeslot:v.getTimeslots()) 
			{
				if(timeslot.equals(t)) 
				{
					v.removeTimeslot(t);
					return;
				}
			}
		}*/
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
}
