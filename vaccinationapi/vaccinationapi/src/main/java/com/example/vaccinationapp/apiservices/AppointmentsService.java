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
	//ArrayList<Αppointment> appointments;
	@Autowired
	ΑppointmentRepository appointmentRepository;
	
	public List<Αppointment> getΑppointments() 
	{
		return appointmentRepository.findAll();
	}
	public Αppointment getAppointmentById(Long id) 
	{
		Optional<Αppointment> byId = appointmentRepository.findById(id);
		if(byId.isPresent()) 
		{
			return byId.get();
		}
		return null;
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
	public void removeΑppointment(Long id) throws Exception
	{
		//try 
		//{
			this.appointmentRepository.deleteById(id);
			/*if(appointmentRepository.findById(id) !=null) 
			{
				Αppointment temp = appointmentRepository.findById(id).get();
				System.out.println("Appointment to delete: "+temp.getId());
				appointmentRepository.delete(temp);
				//appointmentRepository.flush();
				return "";
			}
			return "We did not find Αppointment to delete";
		//}
		/*catch(Exception exc)
		{
			return "Error in removeΑppointment. Exception Message: "+exc.getMessage();
		}*/
	}
	public void updateAppointment(Αppointment appointment,Timeslot newTimeslot ) 
	{
		//Timeslot oldTimeslot  = appointment.getTimeslot();
		//Αppointment a = this.getAppointmentById(appointment.getId());
		//a.setTimeslot(newTimeslot);
		//a.setChanges(a.getChanges()-1);
		for(Αppointment a:this.appointmentRepository.findAll()) 
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
	public void updateAppointmentStatus(Long id,boolean status) 
	{
		Αppointment a = getAppointmentById(id);
		a.setActive(status);
		appointmentRepository.save(a);
	}
}
