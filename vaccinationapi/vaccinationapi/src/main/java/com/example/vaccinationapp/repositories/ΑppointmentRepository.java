package com.example.vaccinationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vaccinationapp.entities.Appointment;

public interface ΑppointmentRepository extends JpaRepository<Appointment, Long> {

}
