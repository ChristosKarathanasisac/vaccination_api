package com.example.vaccinationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vaccinationapp.entities.Vaccination;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

}
