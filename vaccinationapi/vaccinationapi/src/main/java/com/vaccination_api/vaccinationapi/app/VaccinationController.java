package com.vaccination_api.vaccinationapi.app;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vaccinationapp.apiservices.*;
import com.example.vaccinationapp.entities.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication(scanBasePackages={
"com.example.vaccinationapp.apiservices", "com.vaccination_api.vaccinationapi"})
public class VaccinationController {
	//private User user;
	@Autowired
	private TimeslotsService  timeslotService;
	@Autowired
	private AppointmentsService  appointmentsService;
	
	
	////////////////////////////FOR TEST ONLY//////////////////////////////////////////////
	///////////////////////WE SHOULD RUN THIS BEFORE DO ANYTHING///////////////////////////
	
	//http://localhost:8080/init
	@GetMapping(path="/init")
	public void intapp()  throws Exception{
		appointmentsService.InitServiceForTestOnly();
		timeslotService.InitServiceForTestOnly();
		return;
	} 
   ////////////////////////////////////////////////////////////////////////////////////
	
	
	//http://localhost:8080/getAvailableTimeslots?day=09&month=01&year=2023&vacCenterCode=001
	//SHOULD I DO AND THIS POST????
	@GetMapping(path="/getAvailableTimeslots")
	public ArrayList<Timeslot> getAvailableTimeslots(@RequestParam(value="day") String day,
			@RequestParam(value="month") String month,
			@RequestParam(value="year") String year,
			@RequestParam(value="vacCenterCode") String vacCenterCode)  throws Exception{
		System.out.println("We search for: ");
		System.out.println("Vaccination Center: "+vacCenterCode);
		System.out.println("day: "+day);
		System.out.println("month: "+month);
		System.out.println("year: "+year);
		ArrayList<Timeslot> test = this.timeslotService.getTimeslotsByDate(day.trim(),month.trim(),year.trim(),vacCenterCode);
		System.out.println("It will return: "+test.size());
		return test;
	} 
	
	@GetMapping(path="/getVaccinationCenters")
	public ArrayList<VaccinationCenter> getVaccinationCenters()  throws Exception{
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
	@GetMapping(path="/showVaccinationStatus")
	public String showVaccinationStatus()  throws Exception{
		return "";
	} 
	@PostMapping(path="/bookVaccination")
	public void bookVaccination(@RequestBody Timeslot timeslot)  throws Exception{
		System.out.println("Inside bookVaccination");
		//I will probably have the citizens's data from LOGIN
		Citizen citizen = new Citizen("123456789","Kostas","Papadopoulos","987654321","pap@gmail.com");
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
	@GetMapping(path="/getAppointments")
	public ArrayList<Αppointment> getAppointments()  throws Exception{
		return appointmentsService.getΑppointments();
	} 
	
	@GetMapping(path="/insertVaccination")
	public String insertVaccination()  throws Exception{
		return "";
	} 
	//http://localhost:8080/insertTimeSlot?day=1&hour=11&minute=30
	@PostMapping(path="/insertTimeSlot")
	public void insertTimeSlot(@RequestBody Timeslot timeslot)  throws Exception{
		//We should get doctor by login!!
		//Doctor d = new Doctor("11029711111","Christos","Karathanasis");
		//Timeslot timeslot = req.getTimeslot();
		//String vaccinationCenterCode = req.getVaccinationCenterCode();
		//if((timeslot==null)||(vaccinationCenterCode==null)||(vaccinationCenterCode.isBlank())) 
		if((timeslot==null)) 
		{
			System.out.println("Problem in request");
		}
		//timeslot.setDoc(d);
		this.timeslotService.addTimeslot(timeslot);
	} 
	
	    //http://localhost:8080/insertTimeSlot?day=06&month=01&year=2023&startHour=09&startMinute=00&endHour=09&endMinute=30&vacCenterCode=001
		/*@GetMapping(path="/insertTimeSlot")
		public void insertTimeSlot(@RequestParam(value="day") String day,
				@RequestParam(value="month") String month,
				@RequestParam(value="year") String year,
				@RequestParam(value="startHour") String startHour,
				@RequestParam(value="startMinute") String startMinute,
				@RequestParam(value="endHour") String endHour,
				@RequestParam(value="endMinute") String endMinute,
				@RequestParam(value="vacCenterCode") String vacCenterCode)  throws Exception{
			//For Test////////////////////////////////
			System.out.println("We are inside insertTimeSlot");
			Doctor d = new Doctor("11029711111","Christos","Karathanasis");
			Timeslot t = new Timeslot(day,month,year,startHour,startMinute,endHour,endMinute,d);
			System.out.println("Timeslot Created: ");
			System.out.println(day);
			System.out.println(month);
			System.out.println(year);
			System.out.println(startHour);
			System.out.println(startMinute);
			System.out.println(endHour);
			System.out.println(endMinute);
			System.out.println("Vaccination Center: "+vacCenterCode);
			///////////////////////////////////////////
			this.timeslotService.addTimeslot(t,vacCenterCode);
		} 
*/
}
