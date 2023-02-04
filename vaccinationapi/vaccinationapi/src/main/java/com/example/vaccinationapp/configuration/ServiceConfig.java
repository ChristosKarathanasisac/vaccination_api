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
		//The app is on update mode
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
		Citizen c5 = new Citizen("11029001271", "Christos", "Nikou", "123456789","mai23021@gmail.com");
		c5.setPassword(encodedPassword);
		Citizen c6 = new Citizen("52379151901", "Dimitris", "Lamprou", "987654321","dim@gmail.com");
		c6.setPassword(encodedPassword);
		Citizen c7 = new Citizen("98981146918", "Giannis", "Xarizanis", "124106708","laz@gmail.com");
		c7.setPassword(encodedPassword);
		Citizen c8 = new Citizen("03282896285", "Nikos", "Pasxos", "980893831","pol@gmail.com");
		c8.setPassword(encodedPassword);
		Citizen c9 = new Citizen("39309691615", "Fotis", "Karathanasis", "123456789","mai23021@gmail.com");
		c9.setPassword(encodedPassword);
		Citizen c10 = new Citizen("31129089272", "Dimitris", "Nikou", "987654321","dim@gmail.com");
		c10.setPassword(encodedPassword);
		Citizen c11 = new Citizen("66269762789", "Giannis", "Staurou", "124106708","laz@gmail.com");
		c11.setPassword(encodedPassword);
		Citizen c12 = new Citizen("31129001274", "Nikos", "Papadopoulos", "980893831","pol@gmail.com");
		c12.setPassword(encodedPassword);
		Citizen c13 = new Citizen("94594777832", "Christos", "Karathanasis", "123456789","mai23021@gmail.com");
		c13.setPassword(encodedPassword);
		Citizen c14 = new Citizen("72226433885", "Dimitris", "Pasxos", "987654321","dim@gmail.com");
		c14.setPassword(encodedPassword);
		Citizen c15 = new Citizen("25689115123", "Giannis", "Lazarides", "124106708","laz@gmail.com");
		c15.setPassword(encodedPassword);
		Citizen c16 = new Citizen("90749726941", "Nikos", "Polytarxou", "980893831","pol@gmail.com");
		c16.setPassword(encodedPassword);
		Citizen c17 = new Citizen("68187725294", "Christos", "Karathanasis", "123456789","mai23021@gmail.com");
		c17.setPassword(encodedPassword);
		Citizen c18 = new Citizen("60094233695", "Dimitris", "Damalis", "987654321","dim@gmail.com");
		c18.setPassword(encodedPassword);
		Citizen c19 = new Citizen("41535221794", "Giannis", "Danas", "124106708","laz@gmail.com");
		c19.setPassword(encodedPassword);
		Citizen c20 = new Citizen("32917696479", "Nikos", "Manes", "980893831","pol@gmail.com");
		c20.setPassword(encodedPassword);
		
		appService.addCitizen(c1);
		appService.addCitizen(c2);
		appService.addCitizen(c3);
		appService.addCitizen(c4);
		appService.addCitizen(c5);
		appService.addCitizen(c6);
		appService.addCitizen(c7);
		appService.addCitizen(c8);
		appService.addCitizen(c9);
		appService.addCitizen(c10);
		appService.addCitizen(c11);
		appService.addCitizen(c12);
		appService.addCitizen(c13);
		appService.addCitizen(c14);
		appService.addCitizen(c15);
		appService.addCitizen(c16);
		appService.addCitizen(c17);
		appService.addCitizen(c18);
		appService.addCitizen(c19);
		appService.addCitizen(c20);
		*/
	}
	
}
