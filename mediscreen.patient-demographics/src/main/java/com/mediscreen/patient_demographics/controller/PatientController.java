package com.mediscreen.patient_demographics.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.patient_demographics.exception.UserNotFoundException;
import com.mediscreen.patient_demographics.model.Patient;
import com.mediscreen.patient_demographics.service.PatientService;

@RestController
public class PatientController {

	private static final Log logger = LogFactory.getLog(PatientController.class);

	@Autowired
	private PatientService patientService;

// get all the patients in the table in sql database
	@GetMapping("/patients")
	public List<Patient> getPatients() {
		logger.info("getPatients-started-");
		return patientService.getPatients();
	}

//  Get Patient by givenName
	@GetMapping("/patient/given")
	public List<Patient> getPatient(@RequestParam(name = "name") String name) {
		logger.info("getPatient-started-" + name);
		return patientService.getPatient(name);
	}

//  Get Patient by PatientID
	@GetMapping("/patient/{patientId}")
	public Patient getPatientByID(@PathVariable Long patientId) {
		logger.info("getPatientByID-started-" + patientId);
		Patient patient = patientService.getPatientById(patientId);

		if (null == patient) {
			throw new UserNotFoundException("Not found.");

		}
		return patient;
	}

//	Add new Patient
	@PostMapping("/patient/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addPatient(@RequestBody Patient patient) {
		logger.info("addPatient-started-");
		patientService.savePatient(patient);
	}

//	Update Patient
	@PutMapping("/patient")
	public Patient updatePatient(@RequestParam("patientId") Long patientId, @RequestBody Patient patient) {
		logger.info("updatePatient-started-" + patientId);
		return patientService.updatePatient(patientId, patient);
	}
}
