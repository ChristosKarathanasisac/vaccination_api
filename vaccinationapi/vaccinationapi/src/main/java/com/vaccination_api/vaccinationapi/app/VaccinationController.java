package com.vaccination_api.vaccinationapi.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.vaccinationapp.insertclasses.InsertCitizenRequest;
import com.example.vaccinationapp.insertclasses.InsertDoctorRequest;
import com.example.vaccinationapp.requestclasses.RequestBookAppointment;
import com.example.vaccinationapp.requestclasses.RequestInsertVaccination;
import com.example.vaccinationapp.requestclasses.RequestLogin;
import com.example.vaccinationapp.requestclasses.RequestUpdateAppointment;
import com.example.vaccinationapp.responseclasses.Response;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ComponentScan(basePackages = { "com.example.vaccinationapp.*" })
@EntityScan("com.example.vaccinationapp.*")
@EnableJpaRepositories(basePackages = { "com.example.vaccinationapp.repositories" })
public class VaccinationController {

	@Autowired
	private TimeslotsService timeslotService;
	@Autowired
	private AppointmentsService appointmentsService;
	@Autowired
	private VaccinationService vaccinationService;
	@Autowired
	private ApplicationService applicationService;

	
	//Citizen Jobs
	/////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
	
	@GetMapping(path = "/getAvailableTimeslots")
	public Response getAvailableTimeslots(@RequestParam(value = "day") String day,
			@RequestParam(value = "month") String month, @RequestParam(value = "year") String year,
			@RequestParam(value = "vacCenterCode") String vacCenterCode) throws Exception {
		Response resp = new Response();
		if (month == null || year == null || day == null || vacCenterCode == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Some of the input filters was null");
			return resp;
		}
		if(!this.applicationService.dateValidation(day, month, year)) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("The date formatting was wrong or you searching for an older date");
			return resp;
		}
		ArrayList<Timeslot> tmslots = new ArrayList<Timeslot>();
;	    tmslots = this.timeslotService.getTimeslotsByDate(day.trim(), month.trim(), year.trim(),
				vacCenterCode);
		resp.setStatus("SUCCESS");
		resp.setObj(tmslots);
		resp.setResultmsg("OK");
		return resp;
	}

	@GetMapping(path = "/getAvailableTimeslotsByMonth")
	public Response getAvailableTimeslotsByMonth(@RequestParam(value = "month") String month,
			@RequestParam(value = "year") String year, @RequestParam(value = "vacCenterCode") String vacCenterCode)
			throws Exception {
		Response resp = new Response();
		if (month == null || year == null || vacCenterCode == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Some of the input filters was null");
			return resp;
		}
		if(!this.applicationService.dateValidation(month, year)) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("The date formatting was wrong or you searching for an older date");
			return resp;
		}
		ArrayList<Timeslot> tmslots = new ArrayList<Timeslot>();
		tmslots = this.timeslotService.getTimeslotsByMonth(month, year, vacCenterCode);
		System.out.println("Timeslot by month: "+tmslots.size());
		resp.setStatus("SUCCESS");
		resp.setObj(tmslots);
		resp.setResultmsg("OK");
		return resp;
	}

	@PostMapping(path = "/bookAppointment")
	public Response bookAppointment(@RequestBody RequestBookAppointment r) throws Exception {
		Response resp = new Response();
		Citizen citizen = applicationService.getCitizenByAMKA(r.getCitizenAMKA());
		
		//Input Values Check
		if (citizen == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Missing Citizen. The Citizen was not Registered.");
			return resp;
		}
		Timeslot ts = timeslotService.getTimeslotById(r.getTmslotID());

		if (ts == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Missing Timeslot. Wrong timeslot id");
			return resp;
		}
		
		//Citizen Status Check
		Vaccination oldVaccination = this.vaccinationService.getVaccinationByCitizen(r.getCitizenAMKA());
		if (oldVaccination != null) {

			resp.setStatus("ERROR");
			resp.setWarningMessage("Citizen is Vaccinated");
			return resp;

		}

		if (appointmentsService.getAppointmentByCitizen(r.getCitizenAMKA()) != null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Citizen has already Appointment.");
			return resp;
		}
		
		//If all checks are ok-->Create Appointment
		Appointment appointment = new Appointment(citizen, ts);

		appointmentsService.addΑppointment(appointment);
		timeslotService.updateTimeslotStatus(ts.getId(), false);
		resp.setStatus("SUCCESS");
		resp.setResultmsg("Appointment added!");
		return resp;

	}

	@PostMapping(path = "/updateAppointment")
	public Response changeAppointment(@RequestBody RequestUpdateAppointment r) throws Exception {

		//Check for existing appointment
		Response resp = new Response();
		Appointment appointment = this.appointmentsService.getAppointmentById(r.getAppointmentID());
		if (appointment == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Appointment not found.");
			return resp;
		}
		//Check for existing appointment's remaining changes
		if (appointment.getChanges() == 0) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Ιt is not allowed to change the appointment. MAX Changes was 2.");
			return resp;
		}
		
		//Check the validation of the new timeslot
		Timeslot newTimeslot = this.timeslotService.getTimeslotById(r.getNewTimeslotID());
		if (newTimeslot == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Timeslot not found.");
			return resp;
		}
		
		//If all checks are ok-->Update Appointment
		Timeslot oldTimeslot = appointment.getTimeslot();

		timeslotService.updateTimeslotStatus(oldTimeslot.getId(), true);
		appointmentsService.updateAppointment(appointment, newTimeslot);
		timeslotService.updateTimeslotStatus(newTimeslot.getId(), false);

		resp.setStatus("SUCCESS");
		resp.setResultmsg("Appointment updated successfully");
		return resp;
	}

	@GetMapping(path = "/showVaccinationStatus")
	public Response showVaccinationStatus(@RequestParam(value = "amka") String amka) throws Exception {
		Response resp = new Response();
		//Input Values Check
		Citizen c = this.applicationService.getCitizenByAMKA(amka);
		if (c == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Citizen missing");
			return resp;
		}

		//Gets the vaccination Status
		Vaccination vaccination = this.vaccinationService.getVaccinationByCitizen(amka);
		if (vaccination == null) {
			resp.setStatus("SUCCESS");
			resp.setResultmsg("NOT VACCINATED");
			return resp;
		} else {
			resp.setStatus("SUCCESS");
			resp.setResultmsg("VACCINATED");
			resp.setObj(vaccination);
			return resp;
		}

	}

	@PostMapping(path = "/getCitizenAppointment")
	public Response getAppointments(@RequestBody Citizen citizen) throws Exception {
		Response resp = new Response();

		//Input Values Check
		if (citizen == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Citizen in request was null");
			return resp;
		}

		Citizen aCitizen = this.applicationService.getCitizenByAMKA(citizen.getAmka());
		if (aCitizen == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Missing citizen");
			return resp;
		}

		//If all check are ok get the appointment
		Appointment appointment = this.appointmentsService.getAppointmentByCitizen(citizen.getAmka());
		if (appointment == null) {
			resp.setStatus("SUCCESS");
			resp.setResultmsg("appointments was null");
			return resp;
		}
		resp.setStatus("SUCCESS");
		resp.setResultmsg("OK");
		resp.setObj(appointment);
		return resp;

	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	
	//Doctor Jobs
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	
	@PostMapping(path = "/getAppointments")
	public Response getAppointments(@RequestBody Doctor doc) throws Exception {
		//Input Values Check
		Response resp = new Response();

		if (doc == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Doctor in request was null");
			return resp;
		}

		Doctor doctor = this.applicationService.getDoctorByAMKA(doc.getAmka());
		if (doctor == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Missing doctor");
			return resp;
		}

		//If all checks are ok get the appointment
		ArrayList<Appointment> appointments = this.appointmentsService.getΑppointmentsByDoc(doctor.getAmka());
		if (appointments == null) {
			resp.setStatus("SUCCESS");
			resp.setResultmsg("appointments was null");
			return resp;
		}
		resp.setStatus("SUCCESS");
		resp.setResultmsg("OK");
		resp.setObj(appointments);
		return resp;

	}
	
	@PostMapping(path = "/getTodaysAppointments")
	public Response getTodaysAppointments(@RequestBody Doctor doc) throws Exception {
		Response resp = new Response();

		//Input Values Check
		if (doc == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Doctor in request was null");
			return resp;
		}

		Doctor doctor = this.applicationService.getDoctorByAMKA(doc.getAmka());
		if (doctor == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Missing doctor");
			return resp;
		}

		//Get current date
		LocalDate currentdate = LocalDate.now();
		String day = String.valueOf(currentdate.getDayOfMonth());
		
		String month = String.valueOf(currentdate.getMonthValue());
		
		String year = String.valueOf(currentdate.getYear());
		if (currentdate.getDayOfMonth() <= 9) {
			day = "0" + day;
		}
		if (currentdate.getMonthValue() <= 9) {
			month = "0" + month;
		}

		
		
		ArrayList<Appointment> appointments = this.appointmentsService.getΑppointmentsByDay(doctor.getAmka(), day,
				month, year);
		if (appointments == null) {
			resp.setStatus("SUCCESS");
			resp.setResultmsg("appointments was null");
			return resp;
		}
		resp.setStatus("SUCCESS");
		resp.setResultmsg("OK");
		resp.setObj(appointments);
		return resp;

	}

	@PostMapping(path = "/insertVaccination")
	public Response insertVaccination(@RequestBody RequestInsertVaccination r) throws Exception {
		Response resp = new Response();

		//Input values checks
		Vaccination oldVaccination = this.vaccinationService.getVaccinationByCitizen(r.getCitizenAMKA());
		if (oldVaccination != null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Citizen is already vaccinated");
			return resp;
		}

		oldVaccination = this.vaccinationService.getInvalidVaccinationByCitizen(r.getCitizenAMKA());
		if (oldVaccination != null) {
			this.vaccinationService.removeVaccination(oldVaccination.getId());
		}

		Long timeslotToDeleteID = appointmentsService.getAppointmentByCitizen(r.getCitizenAMKA()).getTimeslot().getId();
		this.timeslotService.removeTimeslot(timeslotToDeleteID);
		// Input Checks
		Doctor doctor = applicationService.getDoctorByAMKA(r.getDoctorAMKA());
		if (doctor == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("The Doctor was not Registered");
			return resp;
		}
		Citizen citizen = applicationService.getCitizenByAMKA(r.getCitizenAMKA());
		if (citizen == null) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("The Citizen was not Registered");
			return resp;
		}

		//Insert the vaccination
		Vaccination vac = new Vaccination(citizen, doctor, r.getDate(), DateUtils.addMonths(r.getDate(), 6));
		vaccinationService.addVaccination(vac);

		resp.setStatus("SUCCESS");
		resp.setResultmsg("Vaccination Added");
		return resp;
	}

	@PostMapping(path = "/insertTimeSlot")
	public Response insertTimeSlot(@RequestBody Timeslot timeslot) throws Exception {
		//Timeslot existance check
		Response resp = new Response();
		if ((timeslot == null)) {
			resp.setStatus("ERROR");
			resp.setWarningMessage("Timeslot was null");
			return resp;
		}
		
		//Timeslot date and time check
		if(!this.applicationService.dateValidation(timeslot.getDay(), timeslot.getMonth(),timeslot.getYear())) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("The date formatting was wrong or you try to insert an older date");
			return resp;
		}
		
		if(!this.applicationService.timeValidation(timeslot)) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("The time formatting was wrong or the end hour was before start hour");
			return resp;
		}
		//Doctor check
		Doctor doctor = this.applicationService.getDoctorByAMKA(timeslot.getDoc().getAmka());
		if(doctor==null) 
		{
			resp.setStatus("ERROR");
			resp.setWarningMessage("Doctor was null");
			return resp;
		}

		
		//Check if the doctor has timeslot or appointment at the same time
		String result = this.timeslotService.checkIfTimislotExist(timeslot);

		if (!result.isBlank()) {
			resp.setStatus("ERROR");
			resp.setWarningMessage(result);
			return resp;
		}

		result = this.appointmentsService.checkIfAppointmentExist(timeslot);
		if (!result.isBlank()) {
			resp.setStatus("ERROR");
			resp.setWarningMessage(result);
			return resp;
		}
		
		//If all checks are ok insert the timeslot
		this.timeslotService.addTimeslot(timeslot);
		resp.setStatus("SUCCESS");
		resp.setResultmsg("Timeslot Added");
		return resp;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	
	
	//General Jobs
	/////////////////////////////////////////////////////////////////////////////
		@GetMapping(path = "/getVaccinationCenters")
		public List<VaccinationCenter> getVaccinationCenters() throws Exception {
			return this.applicationService.getVaccinationCenters();

		}
		
		@PostMapping(path = "/login")
		public Response login(@RequestBody RequestLogin r) throws Exception {
			Response resp = new Response();

			//Check the input values
			if(r==null) 
			{
				resp.setStatus("ERROR");
				resp.setResultmsg("Empty Request");
				return resp;
			}
			//Check who tries to login

			boolean flag=false;
			if(r.isDoctor()) 
			{
				flag = this.applicationService.isRegisteredDoctor(r.getAmka(), r.getPassword());
			}else 
			{
				flag = this.applicationService.isRegisteredCitizen(r.getAmka(),r.getPassword());
			}
			
			if(flag) 
			{
				resp.setStatus("SUCCESS");
				resp.setResultmsg("OK");
				return resp;
			}
			resp.setStatus("ERROR");
			resp.setResultmsg("User missing.");
			return resp;
		}
		
   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////FOR TEST//////////////////////////////////
	/////////////YOU CAN ADD CITIZENS AND DOCTORS VIA SWAGGER/////////////// 
	@PostMapping(path = "/insertCitizen")
	public void insertCitizen(@RequestBody InsertCitizenRequest r) throws Exception {

		if(r==null) 
		{
			return;
		}
		Citizen citizen = this.applicationService.getCitizenByAMKA(r.getAmka());
		if(citizen!=null) 
		{
			return;
		}
		
		citizen = new Citizen();
		citizen.setAmka(r.getAmka());
		citizen.setAfm(r.getAfm());
		citizen.setEmail(r.getEmail());
		citizen.setFirstName(r.getFirstName());
		citizen.setLastName(r.getLastName());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(r.getPassword());
		citizen.setPassword(encodedPassword);
		
		this.applicationService.addCitizen(citizen);
	}
	
	@PostMapping(path = "/insertDoctor")
	public void insertDoctor(@RequestBody InsertDoctorRequest r) throws Exception {

		if(r==null) 
		{
			return;
		}
		Doctor doctor = this.applicationService.getDoctorByAMKA(r.getAmka());
		if(doctor!=null) 
		{
			return;
		}
		
		doctor = new Doctor();
		doctor.setAmka(r.getAmka());
		doctor.setFirstName(r.getFirstName());
		doctor.setLastName(r.getLastName());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(r.getPassword());
	    doctor.setPassword(encodedPassword);
		
		this.applicationService.addDoctor(doctor);
	}
	
	
}
