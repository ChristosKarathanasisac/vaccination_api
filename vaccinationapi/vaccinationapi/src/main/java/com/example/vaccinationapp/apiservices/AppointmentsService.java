package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.repositories.ΑppointmentRepository;

@Service
public class AppointmentsService {
	@Autowired
	ΑppointmentRepository appointmentRepository;
	
	public List<Appointment> getΑppointments() 
	{
		return appointmentRepository.findAll();
	}
	public Appointment getAppointmentById(Long id) 
	{
		Optional<Appointment> byId = appointmentRepository.findById(id);
		if(byId.isPresent()) 
		{
			return byId.get();
		}
		return null;
	}
	public ArrayList<Appointment> getΑppointmentsByDoc(Doctor doc) 
	{
		ArrayList<Appointment> appointmentsByDoc = new ArrayList<Appointment>();
		for(Appointment a:appointmentRepository.findAll()) 
		{
			if(a.getTimeslot().getDoc().equals(doc))
			{
				appointmentsByDoc.add(a);
			}
		}
		return appointmentsByDoc;
	}
	public ArrayList<Appointment> getΑppointmentsByDoc(String docAMKA) 
	{
		ArrayList<Appointment> appointmentsByDoc = new ArrayList<Appointment>();
		for(Appointment a:appointmentRepository.findAll()) 
		{
			if(a.getTimeslot().getDoc().getAmka().equals(docAMKA))
			{
				//appointmentsByDoc.add(appointmentRepository.findById(a.getId()).get());
				appointmentsByDoc.add(a);
			}
		}
		return appointmentsByDoc;
	}
	public ArrayList<Appointment> getΑppointmentsByDay(String docAMKA,String day,String month,String year)
	{
		ArrayList<Appointment> appointmentsByDay = new ArrayList<Appointment>();
		
		for(Appointment a:appointmentRepository.findAll()) 
		{
			if(a.getTimeslot().getDay().equals(day) && a.getTimeslot().getMonth().equals(month) && 
					a.getTimeslot().getYear().equals(year))
			{
				appointmentsByDay.add(a);
			}
		}
		return appointmentsByDay;
	}

	
	public void addΑppointment(Appointment appointment) 
	{
		appointmentRepository.save(appointment);
	}
	public AppointmentsService() {
		super();
	}
	public void removeΑppointment(Long id) throws Exception
	{
		this.appointmentRepository.deleteById(id);	
	}
	public void updateAppointment(Appointment appointment,Timeslot newTimeslot ) 
	{
		
		for(Appointment a:this.appointmentRepository.findAll()) 
		{
			if(a.getId() == appointment.getId()) 
			{
				a.removeTimeslot();
				a.setTimeslot(newTimeslot);
				a.setChanges(a.getChanges()-1);
				this.appointmentRepository.save(a);
				return;
			}
		}
		appointment.setTimeslot(newTimeslot);
		appointmentRepository.save(appointment);
	}
	public Appointment getAppointmentByCitizen(String amka) 
	{
		for(Appointment a:appointmentRepository.findAll()) 
		{
			if(a.getCitizen().getAmka().equals(amka)) 
			{
				return a;
			}
		}
		return null;
	}
	public String checkIfAppointmentExist(Timeslot aTimeslot) 
	{
		for(Appointment a:this.appointmentRepository.findAll()) 
		{
			Timeslot t = a.getTimeslot();
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
			
			return "You can not add this timeslot. You already have an appointment at "+t.getTimeslotDateString()+ " ("
			+t.getTimeslotStartTimeString()+"-"+t.getTimeslotEndTimeString()+")";
			
			
		}
		return "";
	}
}
