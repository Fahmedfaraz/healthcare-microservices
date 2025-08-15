package com.mediscreen.patient_demographics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.patient_demographics.model.Patient;
import com.mediscreen.patient_demographics.service.PatientService;


@RestController
public class PatientController {
	@Autowired
	private PatientService patientService;

	// get all the patients in the table in sql database
	@GetMapping("/patients")
	public List<Patient> getPatients() {
		return patientService.getPatients();
	}

//  Get Patient by givenName
	@GetMapping("/patient")
	public Patient getPatient(@RequestParam(name = "name") String name) {
		Patient patient = patientService.getPatient(name);
		return patient;
	}
	
//  Get Patient by PatientID
	@GetMapping("/patient/{patientId}")
	public Patient getPatientByID(@PathVariable Long patientId) {
		Patient patient = patientService.getPatientById(patientId);
		return patient;
	}

//	Add new Patient
	@PostMapping("/patient/add")
	public void addPatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
	}
	
//	Update Patient
	@PutMapping("/patient")
	 public Patient updatePatient(@RequestParam("patientId") Long patientId, @RequestBody Patient patient) {
		return patientService.updatePatient(patientId, patient);
		
	}
	
	
}
