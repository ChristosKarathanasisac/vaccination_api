package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;


@Service
public class TimeslotsService {
	//ArrayList<Timeslot> timeslots;
	//HashMap<String, ArrayList<Timeslot>> capitalCities = new HashMap<String, ArrayList<Timeslot>>();
	ArrayList<VaccinationCenter> vaccinationCenters;
	public TimeslotsService() {
	}
	public void InitServiceForTestOnly() 
	{
		this.vaccinationCenters = new ArrayList<VaccinationCenter>();
		VaccinationCenter vc1 = new VaccinationCenter("001","Thessaloniki");
		//vc1.setTimeslots(new ArrayList<Timeslot>());
		VaccinationCenter vc2 = new VaccinationCenter("002","Athens");
		//vc2.setTimeslots(new ArrayList<Timeslot>());
		this.vaccinationCenters.add(vc1);
		this.vaccinationCenters.add(vc2);
	}
	
	//public ArrayList<Timeslot> getTimeslots() 
	//{
		//return this.timeslots;
	//}
	public ArrayList<Timeslot> getTimeslotsByDate(String day,String month,String year,String vacCenterCode) 
	{
		System.out.println("We are inside getTimeslotsByDate");
		VaccinationCenter vc = getVaccinationCenterByCode(vacCenterCode);
		if(vc == null) 
		{
			return new ArrayList<Timeslot>();
		}
		ArrayList<Timeslot> timeslotsFilteredByDate =new ArrayList<Timeslot>();
		if(vc.getTimeslots() == null) 
		{
			System.out.println("timeslots was null");
			return new ArrayList<Timeslot>();	
		}
		System.out.println("timeslots count: "+vc.getTimeslots().size());
		for(Timeslot t:vc.getTimeslots()) 
		{
			if((t.getDay().equals(day)) && (t.getMonth().equals(month)) && (t.getYear().equals(year))) 
			{
				timeslotsFilteredByDate.add(t);
			}
		}
		return timeslotsFilteredByDate;
	}
	
	public void addTimeslot(Timeslot timeslot) 
	{
		System.out.println("Inside add Timeslot");
		VaccinationCenter vc = getVaccinationCenterByCode(timeslot.getVaccinationCenterCode());
		/*if(vc == null) 
		{
			System.out.println("vc was null");
			return;
		}
		System.out.println("We try to find vacCenterCode: "+vc.getCode());
		
		//FOR TEST
		if(vc.getTimeslots() == null) 
		{
			System.out.println("vc.getTimeslots() == null");
			//vc.setTimeslots(new ArrayList<Timeslot>());
			return;
		}*/
		////////////////
		if(checkIfTimeslotAlreadyExist(timeslot)) 
		{
			return;
		}
		vc.addTimeslot(timeslot);
		System.out.println("Timeslot Added");
	}
	public ArrayList<VaccinationCenter> getAllVaccinationCenters()
	{
		if(this.vaccinationCenters==null) 
		{
			return new ArrayList<VaccinationCenter>();
		}
		return this.vaccinationCenters;
	}
	private VaccinationCenter getVaccinationCenterByCode(String vacCenterCode) 
	{
		if(this.vaccinationCenters == null) 
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
		return null;
	}
	public void removeTimeslot(Timeslot t) 
	{
		for(VaccinationCenter v:this.vaccinationCenters) 
		{
			for(Timeslot timeslot:v.getTimeslots()) 
			{
				if(timeslot.equals(t)) 
				{
					v.removeTimeslot(t);
					return;
				}
			}
		}
	}
	public boolean checkIfTimeslotAlreadyExist(Timeslot timeslot) 
	{
		VaccinationCenter vc = getVaccinationCenterByCode(timeslot.getVaccinationCenterCode());
		for(Timeslot t:vc.getTimeslots()) 
		{
			if(t.equals(timeslot)) 
			{
				return true;
			}
		}
		return false;
	}
}
