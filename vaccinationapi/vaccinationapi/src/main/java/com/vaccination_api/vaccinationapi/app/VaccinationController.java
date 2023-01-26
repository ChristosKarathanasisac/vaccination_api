package com.vaccination_api.vaccinationapi.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vaccinationapp.apiservices.ApplicationService;
import com.example.vaccinationapp.apiservices.AppointmentsService;
import com.example.vaccinationapp.apiservices.TimeslotsService;
import com.example.vaccinationapp.apiservices.VaccinationService;
import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.requestclasses.RequestBookAppointment;
import com.example.vaccinationapp.requestclasses.RequestInsertVaccination;
import com.example.vaccinationapp.requestclasses.RequestUpdateAppointment;
import com.example.vaccinationapp.responseclasses.Response;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ComponentScan(basePackages = { "com.example.vaccinationapp.*" })
@EntityScan("com.example.vaccinationapp.*")   
@EnableJpaRepositories(basePackages={
"com.example.vaccinationapp.repositories"})
public class VaccinationController {
	//private User user;
	//@SpringBootApplication(scanBasePackages={
	//"com.example.vaccinationapp.apiservices", "com.vaccination_api.vaccinationapi"})
	
	
	@Autowired
	private TimeslotsService  timeslotService;
	@Autowired
	private AppointmentsService  appointmentsService;
	@Autowired
	private VaccinationService  vaccinationService;
	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping(path="/getAvailableTimeslots")
	public Response getAvailableTimeslots(@RequestParam(value="day") String day,
			@RequestParam(value="month") String month,
			@RequestParam(value="year") String year,
			@RequestParam(value="vacCenterCode") String vacCenterCode)  throws Exception{
		Response resp = new Response(); 
		if(month == null || year==null || day ==null || vacCenterCode==null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Some of the input filters was null");
			return resp;
		}
		ArrayList<Timeslot> tmslots = this.timeslotService.getTimeslotsByDate(day.trim(),month.trim(),year.trim(),vacCenterCode);
		resp.setStatus("SUCCESS");
		resp.setObj(tmslots);
		resp.setResultmsg("OK");
		return resp;
	} 
	@GetMapping(path="/testdelete")
	public void getAvailableTimeslots()  throws Exception{
		this.timeslotService.removeTimeslot(Long.valueOf(1));
		//this.appointmentsService.removeΑppointment(Long.valueOf(1));
	} 
	@GetMapping(path="/getAvailableTimeslotsByMonth")
	public Response  getAvailableTimeslotsByMonth(@RequestParam(value="month") String month,
			@RequestParam(value="year") String year,
			@RequestParam(value="vacCenterCode") String vacCenterCode)  throws Exception{
		Response resp = new Response();
		if(month == null || year==null  || vacCenterCode==null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Some of the input filters was null");
			return resp;
		}
		ArrayList<Timeslot> tmslots = this.timeslotService.getTimeslotsByMonth(month,year,vacCenterCode);
		resp.setStatus("SUCCESS");
		resp.setObj(tmslots);
		resp.setResultmsg("OK");
		return resp;
	}
	
	@GetMapping(path="/getVaccinationCenters")
	public List<VaccinationCenter> getVaccinationCenters()  throws Exception{
		//return timeslotService.getAllVaccinationCenters();
		return null;
	} 
	
	@PostMapping(path="/bookAppointment")
	public Response bookAppointment(@RequestBody RequestBookAppointment r)  throws Exception{
		Response resp = new Response();
		Citizen citizen = applicationService.getCitizenByAMKA(r.getCitizenAMKA());
		if(citizen==null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Missing Citizen. The Citizen was not Registered.");
			return resp;
		}
		Timeslot ts = timeslotService.getTimeslotById(r.getTmslotID());
		
		if(ts==null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Missing Timeslot. Wrong timeslot id");
			return resp;
		}
		if(this.vaccinationService.getVaccinationByCitizen(r.getCitizenAMKA()) !=null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Citizen is Vaccinated");
			return resp;
		}
		
		if(appointmentsService.getAppointmentByCitizen(r.getCitizenAMKA()) !=null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Citizen has already Appointment.");
			return resp;
		}
		Αppointment appointment = new Αppointment(citizen,ts);
		
		appointmentsService.addΑppointment(appointment);
		timeslotService.updateTimeslotStatus(ts.getId(), false);
		resp.setStatus("SUCCESS");
		resp.setResultmsg("Appointment added!");
		return resp;
		
	} 

	@PostMapping(path="/updateAppointment")
	public Response changeAppointment(@RequestBody RequestUpdateAppointment r)  throws Exception{
		
		Response resp = new Response();
		Αppointment appointment = this.appointmentsService.getAppointmentById(r.getAppointmentID());
		if(appointment == null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Appointment not found.");
			return resp;
		}
		if(appointment.getChanges() ==0) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Ιt is not allowed to change the appointment. MAX Changes was 2.");
			return resp;
		}
		Timeslot newTimeslot = this.timeslotService.getTimeslotById(r.getNewTimeslotID());
		if(newTimeslot == null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Timeslot not found.");
			return resp;
		}
		Timeslot oldTimeslot  = appointment.getTimeslot();
		
		timeslotService.updateTimeslotStatus(oldTimeslot.getId(), true);
		appointmentsService.updateAppointment(appointment, newTimeslot);
		timeslotService.updateTimeslotStatus(newTimeslot.getId(), false);
		
		resp.setStatus("SUCCESS");
		resp.setResultmsg("Appointment updated successfully");
		return resp;
	} 
	@PostMapping(path="/showVaccinationStatus")
	public Vaccination showVaccinationStatus(@RequestBody Citizen citizen)  throws Exception{
		if(citizen!=null) 
		{
			//return this.vaccinationService.getVaccinationByCitizen(citizen);
		}
		return null;
	} 
	
	
	@PostMapping(path="/getAppointments")
	public ArrayList<Αppointment> getAppointments(@RequestBody Doctor doc)  throws Exception{
		if(!(doc==null))
		{
			return appointmentsService.getΑppointmentsByDoc(doc);
		}else 
		{
			System.out.println("doc was null");
			return null;
		}
		
	} 
	@PostMapping(path="/getAppointmentsByDay")
	public ArrayList<Αppointment> getAppointments(@RequestBody String[] searchFilters)  throws Exception{
		if(!(searchFilters==null))
		{
			String day = searchFilters[0];
			String month = searchFilters[1];
			String year = searchFilters[2];
			String docAMKA = searchFilters[3];
			return appointmentsService.getΑppointmentsByDay(docAMKA, day, month, year);
		}else 
		{
			System.out.println("doc was null");
			return null;
		}
		
	} 
	
	@PostMapping(path="/insertVaccination")
	public Response insertVaccination(@RequestBody RequestInsertVaccination r)  throws Exception{
		Response resp = new Response();
		
		Long timeslotToDeleteID = appointmentsService.getAppointmentByCitizen(r.getCitizenAMKA()).getTimeslot().getId();
		this.timeslotService.removeTimeslot(timeslotToDeleteID);
		//Input Checks
		Doctor doctor = applicationService.getDoctorByAMKA(r.getDoctorAMKA());
		if(doctor==null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("The Doctor was not Registered");
			return resp;
		}
		Citizen citizen = applicationService.getCitizenByAMKA(r.getCitizenAMKA());
		if(citizen==null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("The Citizen was not Registered");
			return resp;
		}
		
		//this.timeslotService.removeTimeslot(appointmentToDelete.getId());
		//this.appointmentsService.removeΑppointment(appointmentToDelete.getId());
		
		//Add Vaccination
		Vaccination vac = new Vaccination(citizen,doctor,r.getDate(),DateUtils.addMonths(r.getDate(), 6));
		vaccinationService.addVaccination(vac);
		//Αppointment appointmentToDelete = appointmentsService.getAppointmentByCitizen(r.getCitizenAMKA());
		//Delete Timeslot and Appointment
		
		/*
		if(appointmentToDelete ==null) 
		{
			resp.setStatus("ERROR");
			resp.setResultmsg("Dont find appointmentToDelete");
			return resp;
		} 
		
		String res = timeslotService.removeTimeslot(appointmentToDelete.getTimeslot().getId());
		if(!res.isBlank())
	    {
			resp.setStatus("ERROR");
			resp.setWarningMessage(res);
			return resp;
	    }
		
		res = appointmentsService.removeΑppointment(appointmentToDelete.getId());
		if(!res.isBlank())
	    {
			resp.setStatus("ERROR");
			resp.setWarningMessage(res);
			return resp;
	    }
		
		
		*/
		//appointmentsService.updateAppointmentStatus(appointmentToDelete.getId(), false);
	    //timeslotService.updateTimeslotStatus(appointmentToDelete.getTimeslot().getId(), false);
		//Αppointment appointmentToDelete = appointmentsService.getAppointmentByCitizen(r.getCitizenAMKA());
		resp.setStatus("SUCCESS");
		resp.setResultmsg("Vaccination Added");
		return resp;
	} 
	//http://localhost:8080/insertTimeSlot?day=1&hour=11&minute=30
	@PostMapping(path="/insertTimeSlot")
	public void insertTimeSlot(@RequestBody Timeslot timeslot)  throws Exception{
		if((timeslot==null)) 
		{
			System.out.println("Problem in request");
		}
		//timeslot.createId();
		this.timeslotService.addTimeslot(timeslot);
	} 
}
