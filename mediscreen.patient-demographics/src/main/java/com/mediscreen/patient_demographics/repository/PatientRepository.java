package com.mediscreen.patient_demographics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mediscreen.patient_demographics.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	   @Query(value = "SELECT * FROM patient WHERE givenName = ?1", nativeQuery = true)
	   Patient findByGivenName(String name);
	   Patient findByPatientId(Long patientId);
	}