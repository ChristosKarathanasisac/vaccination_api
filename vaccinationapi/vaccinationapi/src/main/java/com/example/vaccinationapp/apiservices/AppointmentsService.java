package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.repositories.ΑppointmentRepository;

@Service
public class AppointmentsService {
	//ArrayList<Αppointment> appointments;
	@Autowired
	ΑppointmentRepository appointmentRepository;
	
	public List<Αppointment> getΑppointments() 
	{
		return appointmentRepository.findAll();
	}
	public ArrayList<Αppointment> getΑppointmentsByDoc(Doctor doc) 
	{
		ArrayList<Αppointment> appointmentsByDoc = new ArrayList<Αppointment>();
		for(Αppointment a:appointmentRepository.findAll()) 
		{
			if(a.getTimeslot().getDoc().equals(doc))
			{
				appointmentsByDoc.add(a);
			}
		}
		return appointmentsByDoc;
	}
	public ArrayList<Αppointment> getΑppointmentsByDoc(String docAMKA) 
	{
		ArrayList<Αppointment> appointmentsByDoc = new ArrayList<Αppointment>();
		for(Αppointment a:appointmentRepository.findAll()) 
		{
			if(a.getTimeslot().getDoc().getAmka().equals(docAMKA))
			{
				appointmentsByDoc.add(a);
			}
		}
		return appointmentsByDoc;
	}
	public ArrayList<Αppointment> getΑppointmentsByDay(String docAMKA,String day,String month,String year)
	{
		//ArrayList<Αppointment> appointmentsByDoc = getΑppointmentsByDoc(docAMKA);
		ArrayList<Αppointment> appointmentsByDay = new ArrayList<Αppointment>();
		
		for(Αppointment a:appointmentRepository.findAll()) 
		{
			if(a.getTimeslot().getDay().equals(day) && a.getTimeslot().getMonth().equals(month) && 
					a.getTimeslot().getYear().equals(year))
			{
				appointmentsByDay.add(a);
			}
		}
		return appointmentsByDay;
	}

	
	public void addΑppointment(Αppointment appointment) 
	{
		appointmentRepository.save(appointment);
	}
	public AppointmentsService() {
		super();
	}
	public boolean removeΑppointment(Long id) 
	{
		if(appointmentRepository.findById(id) !=null) 
		{
			appointmentRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public Timeslot updateAppointment(Αppointment newΑppointment) 
	{
		Timeslot oldTimeslot;
		for(Αppointment a:appointmentRepository.findAll()) 
		{
			if(a.getCitizen().equals(newΑppointment.getCitizen())) 
			{
				if(a.getChanges()>0) 
				{
					a.setChanges(a.getChanges()-1);
					oldTimeslot = a.getTimeslot();
					a.setTimeslot(newΑppointment.getTimeslot());
					return oldTimeslot;
				}
				
			}
		}
		return null;
	}
	public Αppointment getAppointmentByCitizen(String amka) 
	{
		for(Αppointment a:appointmentRepository.findAll()) 
		{
			if(a.getCitizen().getAmka().equals(amka)) 
			{
				return a;
			}
		}
		return null;
	}
	
}
