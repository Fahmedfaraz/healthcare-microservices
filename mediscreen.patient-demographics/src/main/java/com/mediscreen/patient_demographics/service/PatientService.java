package com.mediscreen.patient_demographics.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patient_demographics.exception.UserNotFoundException;
import com.mediscreen.patient_demographics.model.Patient;
import com.mediscreen.patient_demographics.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

//	Constructor injection
	public PatientService(PatientRepository patientRepository) {
	        this.patientRepository = patientRepository;
	    }
	// Get all Patients
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

//	// Get Patient by givenName
	public List<Patient> getPatient(String name) {
		List<Patient> patient = patientRepository.findAllByGiven(name);
		if (patient != null && !patient.isEmpty()) {
			return patient;
		} else {
			throw new UserNotFoundException("Patient not found with name: " + name);
		}

	}

	public Patient getPatientById(Long patientId) {
		Patient patient = patientRepository.findByPatientId(patientId);
		if (patient != null) {
			return patient;
		} else {
			throw new UserNotFoundException("Patient not found with PatientID: " + patientId);
		}
	}
//	Add new patient
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

//	Update patient information
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
		} else {
			throw new UserNotFoundException("Patient not found with name: " + patient);
		}
	}

}
