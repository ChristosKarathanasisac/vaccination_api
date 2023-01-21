package com.vaccination_api.vaccinationapi.app;

import java.util.ArrayList;
import java.util.List;

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

import com.example.vaccinationapp.apiservices.AppointmentsService;
import com.example.vaccinationapp.apiservices.TimeslotsService;
import com.example.vaccinationapp.apiservices.VaccinationService;
import com.example.vaccinationapp.entities.*;



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
	
	
	@GetMapping(path="/getAvailableTimeslots")
	public ArrayList<Timeslot> getAvailableTimeslotsTest(@RequestParam(value="day") String day,
			@RequestParam(value="month") String month,
			@RequestParam(value="year") String year,
			@RequestParam(value="vacCenterCode") String vacCenterCode)  throws Exception{
		
		if(month == null || year==null || day ==null || vacCenterCode==null) 
		{
			return null;
		}
		ArrayList<Timeslot> tmslots = this.timeslotService.getTimeslotsByDate(day.trim(),month.trim(),year.trim(),vacCenterCode);
		return tmslots;
	} 
	
	/*@PostMapping(path="/getAvailableTimeslots")
	public ArrayList<Timeslot> getAvailableTimeslots(@RequestBody String[] searchFilters)  throws Exception{
		ArrayList<Timeslot> test = this.timeslotService.getTimeslotsByDate(searchFilters[0].trim(),searchFilters[1].trim(),
				searchFilters[2].trim(),searchFilters[3].trim());
		//System.out.println("It will return: "+test.size());
		return test;
	} */
	/*@PostMapping(path="/getAvailableTimeslotsByMonth")
	public ArrayList<Timeslot> getAvailableTimeslotsByMonth(@RequestBody String[] searchFilters)  throws Exception{
		return this.timeslotService.getTimeslotsByMonth(searchFilters[0].trim(),searchFilters[1].trim(),
				searchFilters[2].trim());
	}*/ 
	@GetMapping(path="/getAvailableTimeslotsByMonth")
	public ArrayList<Timeslot> getAvailableTimeslotsByMonth(@RequestParam(value="month") String month,
			@RequestParam(value="year") String year,
			@RequestParam(value="vacCenterCode") String vacCenterCode)  throws Exception{
		if(month == null || year==null  || vacCenterCode==null) 
		{
			return null;
		}
		return this.timeslotService.getTimeslotsByMonth(month,year,
				vacCenterCode);
	}
	
	@GetMapping(path="/getVaccinationCenters")
	public List<VaccinationCenter> getVaccinationCenters()  throws Exception{
		return timeslotService.getAllVaccinationCenters();
	} 
	
	//@GetMapping(path="/bookVaccination")
	//public void bookVaccination()  throws Exception{
		//return;
	//} 
	@PostMapping(path="/updateAppointment")
	public void changeAppointment(@RequestBody Timeslot timeslot)  throws Exception{
		//Timeslot timeslot = req.getTimeslot();
		//String vaccinationCenterCode = req.getVaccinationCenterCode();
		Citizen citizen = new Citizen("123456789","Kostas","Papadopoulos","987654321","pap@gmail.com");
		if(!appointmentsService.checkIfCitizenHasAppointment(citizen)) 
		{
			return;
		}
		Αppointment appointment = new Αppointment(citizen,timeslot);
		Timeslot oldtimeslot = appointmentsService.updateAppointment(appointment);
		timeslotService.removeTimeslot(timeslot);
		if(oldtimeslot==null) {return;}
		timeslotService.addTimeslot(oldtimeslot);
		return;
		
	} 
	@PostMapping(path="/showVaccinationStatus")
	public Vaccination showVaccinationStatus(@RequestBody Citizen citizen)  throws Exception{
		if(citizen!=null) 
		{
			return this.vaccinationService.getVaccinationByCitizen(citizen);
		}
		return null;
	} 
	@PostMapping(path="/bookVaccination")
	public void bookVaccination(@RequestBody Timeslot timeslot)  throws Exception{
		System.out.println("Inside bookVaccination");
		//I will probably have the citizens's data from LOGIN
		Citizen citizen = new Citizen("123456789","Kostas","Papadopoulos","987654321","pap@gmail.com");
		if(this.vaccinationService.getVaccinationByCitizen(citizen) !=null) 
		{
			System.out.println("The citizen is Vaccinated");
			return;
		}
		if(timeslot ==null) 
		{
			System.out.println("Timeslot was null");
		}
		if(appointmentsService.checkIfCitizenHasAppointment(citizen)) 
		{
			return;
		}
		Αppointment appointment = new Αppointment(citizen,timeslot);
		
		appointmentsService.addΑppointment(appointment);
		timeslotService.removeTimeslot(timeslot);
		
	} 
	//localhost:8080/getAppointments
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
	public void insertVaccination(@RequestBody Vaccination vaccination)  throws Exception{
		this.appointmentsService.removeAppointmentByCitizen(vaccination.getCitizen());
		this.vaccinationService.addVaccination(vaccination);
	} 
	//http://localhost:8080/insertTimeSlot?day=1&hour=11&minute=30
	@PostMapping(path="/insertTimeSlot")
	public void insertTimeSlot(@RequestBody Timeslot timeslot)  throws Exception{
		if((timeslot==null)) 
		{
			System.out.println("Problem in request");
		}
		timeslot.createId();
		this.timeslotService.addTimeslot(timeslot);
	} 
}
