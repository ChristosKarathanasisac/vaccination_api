package com.example.vaccinationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vaccinationapp.entities.*;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {

}
