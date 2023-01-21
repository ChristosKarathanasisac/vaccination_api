package com.example.vaccinationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vaccinationapp.entities.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, String> {

}
