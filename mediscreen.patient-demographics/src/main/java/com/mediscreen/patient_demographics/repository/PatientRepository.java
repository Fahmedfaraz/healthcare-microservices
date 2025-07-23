package com.mediscreen.patient_demographics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mediscreen.patient_demographics.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	   @Query(value = "SELECT * FROM patient WHERE givenName = ?1", nativeQuery = true)
	   Patient findByGivenName(Patient name);
	}