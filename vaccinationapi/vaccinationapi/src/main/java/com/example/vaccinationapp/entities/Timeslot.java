package com.example.vaccinationapp.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Timeslot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	private String day;
	private String month;
	private String year;
	private String startHour;
	private String startMin;
	private String endHour;
	private String endMin;
	private boolean available;

	@ManyToOne
	@JoinColumn(name = "vaccinationCenter_name")
	private VaccinationCenter vacCenter;
	@ManyToOne()
	@JoinColumn(name = "doctor_amka")
	//@JsonIgnore
	private Doctor doc;

	@OneToOne(mappedBy = "timeslot", orphanRemoval = true)
	@JsonBackReference
	private Appointment appointment;

	//////////////////////// Constructors//////////////////////////////////
	public Timeslot() {

	}

	
	

	public Timeslot(String day, String month, String year, String startHour, String startMin, String endHour,
			String endMin, Doctor doctor, VaccinationCenter vCenter) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.startHour = startHour;
		this.startMin = startMin;
		this.endHour = endHour;
		this.endMin = endMin;
		this.doc = doctor;
		this.vacCenter = vCenter;
		this.available = true;
	}

	//////////////////////// Getters and Setters///////////////////////////
	
	
	
	public Long getId() {
		return id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMin() {
		return startMin;
	}

	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getEndMin() {
		return endMin;
	}

	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}

	public Doctor getDoc() {
		return doc;
	}
	public void setDoc(Doctor doc) {
		this.doc = doc;
	}

	public VaccinationCenter getVacCenter() {
		return vacCenter;
	}

	public void setVacCenter(VaccinationCenter vacCenter) {
		this.vacCenter = vacCenter;
	}
	
	public Appointment getAppointment() {
		return appointment;
	}

	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void removeAppointment() {
		this.appointment = null;
	}

	////////////////////// Methods///////////////////////////

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof Timeslot)) {
			return false;
		}
		Timeslot t = (Timeslot) o;
		return this.id == t.getId();

	}

	public boolean isMatchingToSearch(String day, String month, String year, String vacCenterCode) {
		if (!this.vacCenter.getCode().equals(vacCenterCode)) {
			return false;
		}
		if (!this.year.equals(year)) {
			return false;
		}
		if (!this.month.equals(month)) {
			return false;
		}
		if (!this.day.equals(day)) {
			return false;
		}
		return true;
	}

	public boolean isMatchingToSearch(String month, String year, String vacCenterCode) {
		if (!this.vacCenter.getCode().equals(vacCenterCode)) {
			return false;
		}
		if (!this.year.equals(year)) {
			return false;
		}
		if (!this.month.equals(month)) {
			return false;
		}
		return true;
	}
	@JsonIgnore
	public Date getTimeslotStartTime() {
		try {
			String timeString = this.startHour + ":" + this.startMin + ":" + "00";
			Date result = new SimpleDateFormat("HH:mm:ss").parse(timeString);
			return result;
		} catch (Exception exc) {
			return null;
		}

	}
	@JsonIgnore
	public Date getTimeslotEndTime() {
		try {
			String timeString = this.endHour + ":" + this.endMin + ":" + "00";
			Date result = new SimpleDateFormat("HH:mm:ss").parse(timeString);
			return result;
		} catch (Exception exc) {
			return null;
		}

	}
	@JsonIgnore
	public String getTimeslotStartTimeString() {
		return this.startHour + ":" + this.startMin;
	}
	@JsonIgnore
	public String getTimeslotEndTimeString() {
		return this.endHour + ":" + this.endMin;
	}
	@JsonIgnore
	public String getTimeslotDateString() {
		return this.year + "-" + this.month + "-" + this.day;
	}
	public boolean timeslotsTimesChecker(Timeslot testingTimeslot) {
		Timeslot existingTimeslot = this;
		Calendar calendarExistingTimeslotStartTime = Calendar.getInstance();
		calendarExistingTimeslotStartTime.setTime(existingTimeslot.getTimeslotStartTime());
		calendarExistingTimeslotStartTime.add(Calendar.DATE, 1);

		Calendar calendarExistingTimeslotEndTime = Calendar.getInstance();
		calendarExistingTimeslotEndTime.setTime(existingTimeslot.getTimeslotEndTime());
		calendarExistingTimeslotEndTime.add(Calendar.DATE, 1);

		Calendar calendarTestingTimeslotStartTime = Calendar.getInstance();
		calendarTestingTimeslotStartTime.setTime(testingTimeslot.getTimeslotStartTime());
		calendarTestingTimeslotStartTime.add(Calendar.DATE, 1);

		Calendar calendarTestingTimeslotEndTime = Calendar.getInstance();
		calendarTestingTimeslotEndTime.setTime(testingTimeslot.getTimeslotEndTime());
		calendarTestingTimeslotEndTime.add(Calendar.DATE, 1);

		boolean conditionAfterBefore = calendarTestingTimeslotStartTime.after(calendarExistingTimeslotStartTime)
				&& calendarTestingTimeslotStartTime.before(calendarExistingTimeslotEndTime);
		
		boolean conditionEquals = calendarTestingTimeslotStartTime.equals(calendarExistingTimeslotStartTime)
				|| calendarTestingTimeslotStartTime.equals(calendarExistingTimeslotEndTime);
		
		if (conditionAfterBefore || conditionEquals) {
			return true;
		}
		
		conditionAfterBefore = calendarTestingTimeslotEndTime.after(calendarExistingTimeslotStartTime)
				&& calendarTestingTimeslotEndTime.before(calendarExistingTimeslotEndTime);
		
		conditionEquals = calendarTestingTimeslotEndTime.equals(calendarExistingTimeslotStartTime)
				|| calendarTestingTimeslotEndTime.equals(calendarExistingTimeslotEndTime);
		
		
		if (conditionAfterBefore || conditionEquals) {
			return true;
		}
		return false;
	}

	public boolean timeslotsDatesChecker(Timeslot testingTimeslot) {
		return (this.year.equals(testingTimeslot.getYear()) && this.month.equals(testingTimeslot.getMonth())
				&& this.day.equals(testingTimeslot.getDay()));
	}
}
