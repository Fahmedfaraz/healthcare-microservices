package com.mediscreen.diabetes_assessment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mediscreen.diabetes_assessment.model.Patient;

@FeignClient(name = "patient-demographics-app", url = "${patient.service.url}")

public interface PatientClient {
	@GetMapping("/patient/{id}" )
//    Patient getPatientById(@PathVariable("id") String id);
	Patient getPatientById(@PathVariable (name = "id") Long patientId);
}
