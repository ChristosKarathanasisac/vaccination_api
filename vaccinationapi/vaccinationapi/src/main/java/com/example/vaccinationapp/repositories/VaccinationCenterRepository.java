package com.example.vaccinationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vaccinationapp.entities.VaccinationCenter;

public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, String> {

}
