package com.mediscreen.patient_demographics.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patient_demographics.model.Patient;
import com.mediscreen.patient_demographics.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	// Get all Patients
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

//	// Get Patient by givenName
	public Patient getPatient(Patient name) {
//		return patientRepository.findByGivenName(name).orElseThrow(() -> new RuntimeException("Patient not found with name: " + givenName));
		Patient patient = patientRepository.findByGivenName(name);
		if (patient != null) {
			return patient;
		} else {
			throw new RuntimeException("Patient not found with name: " + name);
		}

	}

//	Add new patient
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public Patient updatePatient(Long id,Patient patient) {
		Optional<Patient> existingPatient = patientRepository.findById(id);
		if (existingPatient.isPresent()) {
			Patient update = existingPatient.get();
			update.setFamily(patient.getFamily());
			update.setGiven(patient.getGiven());
			update.setDob(patient.getDob());
			update.setPhone(patient.getPhone());
			update.setAddress(patient.getAddress());
			return patientRepository.save(update);
//			return null;
		} else {
			throw new RuntimeException("Patient not found with name: " + patient);
		}
	}

}
