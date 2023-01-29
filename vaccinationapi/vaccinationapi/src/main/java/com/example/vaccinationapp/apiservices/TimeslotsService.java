package com.example.vaccinationapp.apiservices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.repositories.TimeslotRepository;



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
			if(t.isMatchingToSearch(day, month, year, vacCenterCode) && t.isAvailable()) 
			{
				timeslotsFilteredByDate.add(t);
			}
		}
		return timeslotsFilteredByDate;
	}
	
	public void addTimeslot(Timeslot timeslot) 
	{
		timeslotRepository.save(timeslot);
	}
	
	
	public void removeTimeslot(Long id) throws Exception
	{
		this.timeslotRepository.deleteById(id);
	}
	
	public ArrayList<Timeslot> getTimeslotsByMonth(String month,String year,String vacCenterCode)
	{
		LocalDate currentdate = LocalDate.now();
		
		int curDay = currentdate.getDayOfMonth();
		ArrayList<Timeslot> timeslotsFilteredByMonth = new ArrayList<Timeslot>();
		for(Timeslot t:this.timeslotRepository.findAll()) 
		{
			if(t.isMatchingToSearch(month, year, vacCenterCode) && t.isAvailable()) 
			{
					char temp = t.getDay().charAt(t.getDay().length()-2);
					String timeslotDay = t.getDay();
					if(temp == '0') 
					{
						timeslotDay = String.valueOf(t.getDay().charAt(t.getDay().length()-1));
					}
					if(Integer.valueOf(timeslotDay)<curDay) 
					{
						continue;
					}
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
	public void updateTimeslotStatus(Long id,boolean status) 
	{
		Timeslot t = getTimeslotById(id);
		t.setAvailable(status);
		timeslotRepository.save(t);
	}
	
	public String checkIfTimislotExist(Timeslot aTimeslot) 
	{
		for(Timeslot t:this.timeslotRepository.findAll()) 
		{
			if(!t.getDoc().getAmka().equals(aTimeslot.getDoc().getAmka())) 
			{
				continue;
			}
			
			if(!t.timeslotsDatesChecker(aTimeslot)) 
			{
				continue;
			}
			if(!t.timeslotsTimesChecker(aTimeslot))
			{
				continue;
			}
			
			return "You can not add this timeslot. You already have a timeslot at "+t.getTimeslotDateString()+ " ("
			+t.getTimeslotStartTimeString()+"-"+t.getTimeslotEndTimeString()+")";
			
			
		}
		return "";
	}

}
