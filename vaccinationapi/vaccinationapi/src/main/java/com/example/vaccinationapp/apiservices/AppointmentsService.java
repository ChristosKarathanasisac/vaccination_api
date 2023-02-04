package com.example.vaccinationapp.apiservices;

import java.time.LocalDate;
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

	public AppointmentsService() {
		super();
	}
	
	public List<Appointment> getΑppointments() {
		return appointmentRepository.findAll();
	}

	public Appointment getAppointmentById(Long id) {
		Optional<Appointment> byId = appointmentRepository.findById(id);
		if (byId.isPresent()) {
			return byId.get();
		}
		return null;
	}

	public ArrayList<Appointment> getΑppointmentsByDoc(Doctor doc) {
		ArrayList<Appointment> appointmentsByDoc = new ArrayList<Appointment>();
		for (Appointment a : appointmentRepository.findAll()) {
			if (a.getTimeslot().getDoc().equals(doc)) {
				LocalDate currentdate = LocalDate.now();
				int curMonth = currentdate.getMonthValue();
				int curYear = currentdate.getYear();
				int curDay = currentdate.getDayOfMonth();

				String month = a.getTimeslot().getMonth();
				String year = a.getTimeslot().getYear();
				String day = a.getTimeslot().getDay();

				if (Integer.valueOf(year) < curYear) {
					continue;
				}
				if (Integer.valueOf(year) > curYear) {
					appointmentsByDoc.add(a);
					continue;
				}

				// Check for older month
				char temp = month.charAt(month.length() - 2);
				String examinedMonth = month;
				if (temp == '0') {
					examinedMonth = String.valueOf(month.charAt(month.length() - 1));
				}
				if (Integer.valueOf(examinedMonth) < curMonth) {
					continue;
				}
				if (Integer.valueOf(examinedMonth) > curMonth) {
					appointmentsByDoc.add(a);
					continue;
				}

				// Check for older day
				temp = day.charAt(day.length() - 2);
				String examinedDay = day;
				if (temp == '0') {
					examinedDay = String.valueOf(day.charAt(day.length() - 1));
				}
				if (Integer.valueOf(examinedDay) < curDay) {
					continue;
				}
				appointmentsByDoc.add(a);
			}
		}
		return appointmentsByDoc;
	}

	public ArrayList<Appointment> getΑppointmentsByDoc(String docAMKA) {
		ArrayList<Appointment> appointmentsByDoc = new ArrayList<Appointment>();
		for (Appointment a : appointmentRepository.findAll()) {
			if (a.getTimeslot().getDoc().getAmka().equals(docAMKA)) {
				LocalDate currentdate = LocalDate.now();
				int curMonth = currentdate.getMonthValue();
				int curYear = currentdate.getYear();
				int curDay = currentdate.getDayOfMonth();

				String month = a.getTimeslot().getMonth();
				String year = a.getTimeslot().getYear();
				String day = a.getTimeslot().getDay();

				if (Integer.valueOf(year) < curYear) {
					continue;
				}
				if (Integer.valueOf(year) > curYear) {
					appointmentsByDoc.add(a);
					continue;
				}

				// Check for older month
				char temp = month.charAt(month.length() - 2);
				String examinedMonth = month;
				if (temp == '0') {
					examinedMonth = String.valueOf(month.charAt(month.length() - 1));
				}
				if (Integer.valueOf(examinedMonth) < curMonth) {
					continue;
				}
				if (Integer.valueOf(examinedMonth) > curMonth) {
					appointmentsByDoc.add(a);
					continue;
				}

				// Check for older day
				temp = day.charAt(day.length() - 2);
				String examinedDay = day;
				if (temp == '0') {
					examinedDay = String.valueOf(day.charAt(day.length() - 1));
				}
				if (Integer.valueOf(examinedDay) < curDay) {
					continue;
				}
				appointmentsByDoc.add(a);
			}
		}
		return appointmentsByDoc;
	}

	public ArrayList<Appointment> getΑppointmentsByDay(String docAMKA, String day, String month, String year) {
		ArrayList<Appointment> appointmentsByDay = new ArrayList<Appointment>();

		for (Appointment a : getΑppointmentsByDoc(docAMKA)) {
			if (a.getTimeslot().getDay().equals(day) && a.getTimeslot().getMonth().equals(month)
					&& a.getTimeslot().getYear().equals(year)) {
				appointmentsByDay.add(a);
			}
		}
		return appointmentsByDay;
	}

	public void addΑppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	

	public void removeΑppointment(Long id) throws Exception {
		this.appointmentRepository.deleteById(id);
	}

	public void updateAppointment(Appointment appointment, Timeslot newTimeslot) {

		for (Appointment a : this.appointmentRepository.findAll()) {
			if (a.getId() == appointment.getId()) {
				a.removeTimeslot();
				a.setTimeslot(newTimeslot);
				a.setChanges(a.getChanges() - 1);
				this.appointmentRepository.save(a);
				return;
			}
		}
		appointment.setTimeslot(newTimeslot);
		appointmentRepository.save(appointment);
	}

	public Appointment getAppointmentByCitizen(String amka) {
		for (Appointment a : appointmentRepository.findAll()) {
			if (a.getCitizen().getAmka().equals(amka)) {
				return a;
			}
		}
		return null;
	}

	public String checkIfAppointmentExist(Timeslot aTimeslot) {
		for (Appointment a : this.appointmentRepository.findAll()) {
			Timeslot t = a.getTimeslot();
			if (!t.getDoc().getAmka().equals(aTimeslot.getDoc().getAmka())) {
				continue;
			}

			if (!t.timeslotsDatesChecker(aTimeslot)) {
				continue;
			}
			if (!t.timeslotsTimesChecker(aTimeslot)) {
				continue;
			}

			return "You can not add this timeslot. You already have an appointment at " + t.getTimeslotDateString()
					+ " (" + t.getTimeslotStartTimeString() + "-" + t.getTimeslotEndTimeString() + ")";

		}
		return "";
	}
}
