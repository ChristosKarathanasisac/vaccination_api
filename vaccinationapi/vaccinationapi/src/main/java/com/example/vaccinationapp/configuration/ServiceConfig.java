package com.example.vaccinationapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.vaccinationapp.apiservices.*;
import com.example.vaccinationapp.entities.*;

@Configuration
public class ServiceConfig implements CommandLineRunner{
	@Autowired
	ApplicationService appService;

	@Override
	public void run(String... args) throws Exception {
		/*VaccinationCenter vc1 = new VaccinationCenter("001","Thessaloniki");
		VaccinationCenter vc2 = new VaccinationCenter("002","Athens");
		appService.addVaccinationCenter(vc1);
		appService.addVaccinationCenter(vc2);
		
		Doctor d1 = new Doctor("11029907820","Giorge","Papadopoulos");
		Doctor d2 = new Doctor("16020907830","Christos","Papagewrgiou");
		Doctor d3 = new Doctor("11029707000","Nikos","Pappas");
		Doctor d4 = new Doctor("16028977820","Stelios","Stergiou");
		
		appService.addDoctor(d1);
		appService.addDoctor(d2);
		appService.addDoctor(d3);
		appService.addDoctor(d4);
		
		Citizen c1 = new Citizen("11029001277", "Christos", "Karathanasis", "123456789","mai23021@gmail.com");
		Citizen c2 = new Citizen("31129001278", "Dimitris", "Pasxos", "987654321","dim@gmail.com");
		appService.addCitizen(c1);
		appService.addCitizen(c2);
		*/
	}
}
