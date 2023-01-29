package com.example.vaccinationapp.apiservices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccinationapp.entities.*;
import com.example.vaccinationapp.repositories.TimeslotRepository;

@Service
public class TimeslotsService {
	@Autowired
	TimeslotRepository timeslotRepository;

	public TimeslotsService() {
	}

	public ArrayList<Timeslot> getTimeslotsByDate(String day, String month, String year, String vacCenterCode) {
		ArrayList<Timeslot> timeslotsFilteredByDate = new ArrayList<Timeslot>();
		for (Timeslot t : this.timeslotRepository.findAll()) {
			if (t.isMatchingToSearch(day, month, year, vacCenterCode) && t.isAvailable()) {
				timeslotsFilteredByDate.add(t);
			}
		}
		return timeslotsFilteredByDate;
	}

	public void addTimeslot(Timeslot timeslot) {
		timeslotRepository.save(timeslot);
	}

	public void removeTimeslot(Long id) throws Exception {
		this.timeslotRepository.deleteById(id);
	}

	public ArrayList<Timeslot> getTimeslotsByMonth(String month, String year, String vacCenterCode) {
		LocalDate currentdate = LocalDate.now();

		int curDay = currentdate.getDayOfMonth();
		int curMonth = currentdate.getMonthValue();
		int curYear = currentdate.getYear();
		ArrayList<Timeslot> timeslotsFilteredByMonth = new ArrayList<Timeslot>();
		for (Timeslot t : this.timeslotRepository.findAll()) {
			if (t.isMatchingToSearch(month, year, vacCenterCode) && t.isAvailable()) {
				//If searching year is bigger than current add the timeslot
				if (Integer.valueOf(year) > curYear) {
					timeslotsFilteredByMonth.add(t);
					continue;
				}

				//
				char temp = t.getMonth().charAt(t.getMonth().length() - 2);
				String examinedMonth = t.getMonth();
				if (temp == '0') {
					examinedMonth = String.valueOf(t.getMonth().charAt(t.getMonth().length() - 1));
				}
				//If searching month is bigger than current add the timeslot
				if (Integer.valueOf(examinedMonth) > curMonth) {
					timeslotsFilteredByMonth.add(t);
					continue;
				}

				temp = t.getDay().charAt(t.getDay().length() - 2);
				String timeslotDay = t.getDay();
				if (temp == '0') {
					timeslotDay = String.valueOf(t.getDay().charAt(t.getDay().length() - 1));
				}
				if (Integer.valueOf(timeslotDay) < curDay) {
					continue;
				}
				//If searching day is bigger than current add the timeslot
				timeslotsFilteredByMonth.add(t);
			}
		}
		return timeslotsFilteredByMonth;
	}

	public Timeslot getTimeslotById(Long id) {
		Optional<Timeslot> byId = timeslotRepository.findById(id);
		if (byId.isPresent()) {
			return byId.get();
		}
		return null;
	}

	public void updateTimeslotStatus(Long id, boolean status) {
		Timeslot t = getTimeslotById(id);
		t.setAvailable(status);
		timeslotRepository.save(t);
	}

	public String checkIfTimislotExist(Timeslot aTimeslot) {
		for (Timeslot t : this.timeslotRepository.findAll()) {
			if (!t.getDoc().getAmka().equals(aTimeslot.getDoc().getAmka())) {
				continue;
			}

			if (!t.timeslotsDatesChecker(aTimeslot)) {
				continue;
			}
			if (!t.timeslotsTimesChecker(aTimeslot)) {
				continue;
			}

			return "You can not add this timeslot. You already have a timeslot at " + t.getTimeslotDateString() + " ("
					+ t.getTimeslotStartTimeString() + "-" + t.getTimeslotEndTimeString() + ")";

		}
		return "";
	}

}
