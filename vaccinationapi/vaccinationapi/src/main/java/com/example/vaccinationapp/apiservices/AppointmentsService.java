package com.example.vaccinationapp.apiservices;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;

@Service
public class AppointmentsService {
	ArrayList<Αppointment> appointments;
	
	public void InitServiceForTestOnly() 
	{
		this.appointments = new ArrayList<Αppointment>();
	}
	public ArrayList<Αppointment> getΑppointments() 
	{
		return this.appointments;
	}
	public ArrayList<Αppointment> getΑppointmentsByDoc(Doctor doc) 
	{
		if(this.appointments == null) 
		{
			return null;
		}
		ArrayList<Αppointment> appointmentsByDoc = new ArrayList<Αppointment>();
		for(Αppointment a:this.appointments) 
		{
			if(a.getTimeslot().getDoc().getAmka().equals(doc.getAmka())) 
			{
				appointmentsByDoc.add(a);
			}
		}
		return appointmentsByDoc;
	}
	public ArrayList<Αppointment> getΑppointmentsByDay(Doctor doc,String day,String month,String year)
	{
		if(this.appointments == null) 
		{
			return null;
		}
		ArrayList<Αppointment> appointmentsByDoc = getΑppointmentsByDoc(doc);
		ArrayList<Αppointment> appointmentsByDay = new ArrayList<Αppointment>();
		
		for(Αppointment a:appointmentsByDoc) 
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
		System.out.println("inside addΑppointment");
		if(this.appointments==null) 
		{
			System.out.println("appointments was null");
		}
		this.appointments.add(appointment);
		System.out.println("Αppointment added");
	}
	public AppointmentsService() {
		super();
	}
	public void removeΑppointment(Αppointment appointment) 
	{
		if(this.appointments.contains(appointment)) 
		{
			this.appointments.remove(appointment);
		}
	}
	public Timeslot updateAppointment(Αppointment newΑppointment) 
	{
		Timeslot oldTimeslot;
		for(Αppointment a:this.appointments) 
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
	public boolean checkIfCitizenHasAppointment(Citizen citizen) 
	{
		for(Αppointment a:this.appointments) 
		{
			if(a.getCitizen().equals(citizen)) 
			{
				return true;
			}
		}
		return false;
	}
}
