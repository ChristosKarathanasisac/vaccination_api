package com.example.vaccinationapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode("test");
	    
		Doctor d1 = new Doctor("11029907820","George","Papadopoulos");
		d1.setPassword(encodedPassword);
		Doctor d2 = new Doctor("16020907830","Christos","Papagewrgiou");
		d2.setPassword(encodedPassword);
		Doctor d3 = new Doctor("11029707000","Nikos","Pappas");
		d3.setPassword(encodedPassword);
		Doctor d4 = new Doctor("16028977820","Stelios","Stergiou");
		d4.setPassword(encodedPassword);
		
		appService.addDoctor(d1);
		appService.addDoctor(d2);
		appService.addDoctor(d3);
		appService.addDoctor(d4);
		
		Citizen c1 = new Citizen("11029001271", "Christos", "Karathanasis", "123456789","mai23021@gmail.com");
		c1.setPassword(encodedPassword);
		Citizen c2 = new Citizen("31129001272", "Dimitris", "Pasxos", "987654321","dim@gmail.com");
		c2.setPassword(encodedPassword);
		Citizen c3 = new Citizen("07029031293", "Giannis", "Lazarides", "124106708","laz@gmail.com");
		c3.setPassword(encodedPassword);
		Citizen c4 = new Citizen("31129001274", "Nikos", "Polytarxou", "980893831","pol@gmail.com");
		c4.setPassword(encodedPassword);
		
		appService.addCitizen(c1);
		appService.addCitizen(c2);
		appService.addCitizen(c3);
		appService.addCitizen(c4);
		*/
	}
	
}
