package com.example.vaccinationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vaccinationapp.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
